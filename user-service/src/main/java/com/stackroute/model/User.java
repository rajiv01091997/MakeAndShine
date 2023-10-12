package com.stackroute.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(value = "user")
public class User {
	@Id
    @Email(message = "Enter a valid Email Id")
    private String emailId;
    @NotEmpty(message = "Enter your name")
    private String name;
    @NotNull
    private Gender gender;
    //@Digits(message = "should contain 10 digits", fraction = 0, integer = 10)
    private long mobile;
    //@NotNull
    //@Size(min = 10, max = 90)
    private int age;
    @NotNull
    @Transient
    private String password;

    private byte[] userImage;

    private Role role;
    private Address address;
	
}
