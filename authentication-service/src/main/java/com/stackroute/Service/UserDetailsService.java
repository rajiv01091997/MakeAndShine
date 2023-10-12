package com.stackroute.Service;

import com.stackroute.dto.JwtResponse;
import com.stackroute.dto.LoginRequest;
import com.stackroute.dto.ResetPasswordDto;



public interface UserDetailsService{

   public JwtResponse authenticateUser(LoginRequest loginRequest);
   public String resetPassword(ResetPasswordDto resetPasswordDto);
}
