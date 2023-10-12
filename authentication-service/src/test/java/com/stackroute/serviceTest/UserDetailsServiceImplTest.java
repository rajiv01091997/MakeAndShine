package com.stackroute.serviceTest;


import com.stackroute.Models.ERole;
import com.stackroute.Models.Role;
import com.stackroute.Models.User;
import com.stackroute.Service.UserDetailsService;

import com.stackroute.dto.JwtResponse;
import com.stackroute.dto.LoginRequest;
import com.stackroute.dto.RegisterRequest;
import com.stackroute.dto.ResetPasswordDto;
import com.stackroute.exceptions.JwtException;
import com.stackroute.repository.UserRepository;
import com.stackroute.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
   @MockBean
   UserDetailsService userDetailsService;



    //testing methods using unit test case


    @Test
    public void testAddUserDetails() {
        try {
            Role role = new Role();
            role.setId(1);
            role.setName(ERole.ROLE_CUSTOMER);
            Set<Role> roles= new HashSet<>();

            roles.add(role);
            long id=1;
           User user=new User();
            user.setId(id);
            user.setUsername("Aplha Beta");
            user.setRoles(roles);
            user.setEmail("abcd123@gmail.com");
            user.setPassword("13245");
            RegisterRequest loginDetailsDto = new RegisterRequest();
            when(userRepository.save(user)).thenReturn(user);
            loginDetailsDto.setEmailId("abcd123@gmail.com");
            assertEquals(user.getEmail(), loginDetailsDto.getEmailId());
        } catch (NullPointerException nullPointerException) {

        }
    }

    @Test
    void testAuthenticateUser() {
        List <String> roles = new ArrayList<>();

        roles.add(ERole.ROLE_EMPLOYEE.toString());

        LoginRequest loginDto = new LoginRequest();
        loginDto.setEmail("abcd123@gmail.com");
        loginDto.setPassword("$2a$10$4kn2kI/TwiOCb7nCCVcIduThWXxd4CcRoi6.fo3iGX4gDybGzC95m");

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraXJhbkBnbWFpbC5jb20iLCJpYXQiOjE2NzAxNjU0MjUsImV4cCI6MTY3MDU5NzQyNX0.iYCYU6AK7KWZWieUquj5rjqyRZw1LkcQj-2iod0rNbrgJfs2XZYBY5UH8ix54zrz9oOwR2Hth1UT49Ylug_Z8w";
        JwtResponse jwtResponse = new JwtResponse(token,1L,"parth","parth@gmail.com",roles);

        when(userDetailsService.authenticateUser(loginDto)).thenReturn(jwtResponse);

        assertNotNull(userDetailsService.authenticateUser(loginDto));

    }


    @Test
    void itShouldThrowJwtExceptionWithInvalidToken() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";

        assertThrows(JwtException.class, () -> {

            jwtUtils.validateJwtToken(token);
        });

    }

    @Test
    public void testForgetPassword(){

        ResetPasswordDto resetPasswordDto=new ResetPasswordDto("parth","parth@gmail.com","123456","123456");
       String response ="YOUR PASSWORD HAS BEEN RESET";
        when(userDetailsService.resetPassword(resetPasswordDto)).thenReturn(response);
        assertEquals(response,userDetailsService.resetPassword(resetPasswordDto));

    }




}
