package com.stackroute.dto;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



import com.stackroute.model.Role;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	    private String emailId;
	    private String name;
	    private GenderDto genderDto;
	    private long mobile;
	    private int age;
	    private String password;
	    private byte[] userImage;
	    private Role roleDto;
	    private AddressDto addressDto;
}
