package com.stackroute.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {
    private String UserName;
    private String emailId;
    private String password;
    private Set<String> role;
}
