package com.stackroute.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ResetPasswordDto {
    String username;
    String email;
    String password;
    String confirmPassword;


}
