package com.stackroute.service;

import java.util.List;


import com.stackroute.dto.AddressDto;
import com.stackroute.dto.UserDataDto;
import com.stackroute.dto.UserDto;
import com.stackroute.model.User;

public interface IUserService {
	
	 UserDto addNewUser(UserDto userDto);
	 List<UserDto> getAllUsers();
	 UserDto getUserById(String emailId);
	 UserDataDto updateUser(String emailId, UserDataDto userData);
	//public List<UserDto> getUsersByRole(RoleDto roleDto);
	 User convertToEntity(UserDto userDto);
	 UserDto convertToDto(User user);

	String updateUserImage(String emailId, byte[] image);

    AddressDto updateUserAddress(String emailId, AddressDto addressDto);
}
