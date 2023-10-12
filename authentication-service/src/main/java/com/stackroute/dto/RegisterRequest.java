package com.stackroute.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;
@Data
public class RegisterRequest {


    private String UserName;
    private String emailId;
    private String password;
    private Set<String> role;
}
