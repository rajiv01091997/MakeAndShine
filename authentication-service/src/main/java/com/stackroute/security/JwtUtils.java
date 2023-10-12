package com.stackroute.security;

import com.stackroute.exceptions.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpirationMs;


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new JwtException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");


        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new JwtException(HttpStatus.BAD_REQUEST, "Invalid JWT token");

        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw new JwtException(HttpStatus.BAD_REQUEST, "Expired JWT token");

        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new JwtException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new JwtException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");

        }catch (Exception exception) {
            throw new JwtException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }
}
