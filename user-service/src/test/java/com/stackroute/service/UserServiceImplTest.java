package com.stackroute.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stackroute.dto.UserDataDto;
import com.stackroute.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.dto.UserDto;
import com.stackroute.exceptions.ResourceNotFoundException;
import com.stackroute.model.Address;
import com.stackroute.model.Gender;
import com.stackroute.model.Role;
import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private IUserService userService;
	
	@Test
	void getAllUsersTest() {
		List<User> userList = new ArrayList<>();
		Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
		User user = new User("rahul@", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);
		Address address1 = new Address("12-8","Srinivasa", "Godavari", "AP", 6767);
		User user1 = new User("dinesh1@", "Dinesh", Gender.MALE, 9877, 22, "67890", null,  Role.ROLE_EMPLOYEE,address1);
		Address address2 = new Address("12-8","Sreenu", "kadapa", "TS", 676767);
		User user2 = new User("geetha@", "Geetha", Gender.FEMALE, 9877, 22, "jlkjgrsdht", null,  Role.ROLE_CUSTOMER,address2);
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		when(userRepository.findAll()).thenReturn(Stream.of(user,user1,user2).collect(Collectors.toList()));
		
		List<UserDto> dtos = userService.getAllUsers();
		
		assertEquals(userList.size(), dtos.size());
		
	}
	
	@Test
	void getUserByEmailIdTest()
	{
		Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
		User user = new User("rahul@", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);
		String emailId = "rahul@";
		when(userRepository.findById(emailId)).thenReturn(Optional.of(user));
		
		UserDto userDto = userService.getUserById(emailId);
		
		assertEquals(userDto.getEmailId(), user.getEmailId());
		assertEquals(userDto.getName(), user.getName());
		assertEquals(userDto.getAge(), user.getAge());
	}
	
	@Test
	void getExceptionByWrongEmailId()
	{
		String emailId = "rahul@gmail.com";
		when(userRepository.findById(emailId)).thenReturn(Optional.empty());
		Optional<User> user = userRepository.findById(emailId);
		when(userRepository.findById(emailId)).thenReturn(user);
		//when(userService.getUserById(emailId)).thenThrow(ResourceNotFoundException.class);
		assertTrue(userRepository.findById(emailId).isEmpty());
		assertThrows(ResourceNotFoundException.class, ()->{
			userService.getUserById(emailId);
		});
	}
	
	@Test
	void createNewUserTest() {
		Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
		User user = new User("rahul@", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);
		
		when(userRepository.save(user)).thenReturn(user);
		
		UserDto userDto1 = userService.convertToDto(user);
		UserDto userDto = userService.addNewUser(userDto1);

		assertEquals(userDto.getEmailId(),user.getEmailId());
		assertEquals(userDto.getName(), userDto.getName());
	}
	
	@Test
	void updateUserTest()
	{
		Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
		User user = new User("rahul@gmail.com", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);
		String emailId = "rahul@gmail.com";
		when(userRepository.findById(emailId)).thenReturn(Optional.of(user));
		UserDataDto userData = new UserDataDto("Rahul",9877676,23);
		//UserDto userDto = userService.convertToDto(user);
		userData.setAge(28);
		//Optional<User> user1 = userRepository.findById(emailId);
		given(userService.updateUser(emailId,userData)).willReturn(userData);
		//UserDto userDto2 = userService.getUserById(emailId);
		UserDataDto userDto1 = userService.updateUser(emailId,userData);
		assertEquals(userDto1.getAge(),28);
	}

	@Test
	void updateUserWhenNoUserIsThere()
	{
		String emailId = "rahul@gmail.com";
		when(userRepository.findById(emailId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()->{
			userService.updateUser(emailId,any(UserDataDto.class));
		});
	}

}
