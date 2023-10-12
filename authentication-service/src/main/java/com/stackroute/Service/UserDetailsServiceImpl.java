package com.stackroute.Service;

import com.stackroute.Models.ERole;
import com.stackroute.Models.Role;
import com.stackroute.config.MQConfig;
import com.stackroute.dto.*;
import com.stackroute.exceptions.RoleNotFoundException;
import com.stackroute.repository.RoleRepository;
import com.stackroute.security.JwtUtils;
import com.stackroute.security.UserDetailsImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stackroute.Models.User;
import com.stackroute.repository.UserRepository;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;


    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Token Generation
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new  JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }


    public String resetPassword(ResetPasswordDto resetPasswordDto) {
        String response;
        if (userRepository.existsByEmail(resetPasswordDto.getEmail())) {
            Optional<User> user = userRepository.findByEmail(resetPasswordDto.getEmail());

            if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
                user.get().setPassword(encoder.encode(resetPasswordDto.getPassword()));
                userRepository.save(user.get());
                response="YOUR PASSWORD HAS BEEN RESET";
                //return new ResponseEntity<>("YOUR PASSWORD HAS BEEN RESET", HttpStatus.OK);
            }

        }
        response="YOUR EMAIL ID IS NOT FOUND";

        return  response;

    }

    //rabbit mq
    @RabbitListener(queues = MQConfig.QUEUE)
    public void registerUser(RegisterRequest signUpRequest) {
//        if (userRepository.existsByEmail(signUpRequest.getEmailId())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }

        // Create new user's account
        User user = new User(signUpRequest.getUserName(),
                signUpRequest.getEmailId(),
                encoder.encode(signUpRequest.getPassword()));
        //roles
        Set<String> strRoles = signUpRequest.getRole();
        //set of objects of class role
        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach( role -> {

                if (role.equalsIgnoreCase("Role_Employee")) {
                    // checking in table if the role is present or not
                    Optional<Role> isrole =roleRepository.findByName(ERole.ROLE_EMPLOYEE);

                    if(isrole.isEmpty()){
                        Role adminRole = new Role(1,ERole.ROLE_EMPLOYEE);

                        roleRepository.save(adminRole);
                        roles.add(adminRole);

                    }else {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                }
                if (role.equalsIgnoreCase("Role_customer")) {
                    Optional<Role> isrole =roleRepository.findByName(ERole.ROLE_CUSTOMER);

                    if(isrole.isEmpty()){
                        Role adminRole = new Role(2,ERole.ROLE_CUSTOMER);
                        roleRepository.save(adminRole);
                        roles.add(adminRole);

                    }else {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                }
            });
        }

        //setter of user class
        user.setRoles(roles);
        //saving to the database
        userRepository.save(user);

       // return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }




}
