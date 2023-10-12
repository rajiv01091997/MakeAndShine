package com.stackroute.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.dto.UserDataDto;
import com.stackroute.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.dto.AddressDto;
import com.stackroute.dto.GenderDto;
import com.stackroute.dto.UserDto;
import com.stackroute.exceptions.ResourceAlreadyPresentException;
import com.stackroute.exceptions.ResourceNotFoundException;
import com.stackroute.model.Role;
import com.stackroute.service.IUserService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
class UserContollerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private IUserService userService;

	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private List<UserDto> userList ;
	
	@BeforeEach
	void contextLoads() {
		
		this.userList =  new ArrayList<>();
		
		AddressDto addressDto = new AddressDto("12-2","Sri", "kadapa", "AP", 676767);
		UserDto userDto = new UserDto("rahul@", "Rahul", GenderDto.MALE, 9877, 22, "12345", null, Role.ROLE_CUSTOMER,  addressDto);
		AddressDto addressDto1 = new AddressDto("12-3","Srinivasa", "Godavari", "AP", 6767);
		UserDto userDto1 = new UserDto("dinesh1@", "Dinesh", GenderDto.MALE, 9877, 22, "67890", null,  Role.ROLE_EMPLOYEE,addressDto1);
		AddressDto addressDto2 = new AddressDto("12-3","Sreenu", "kadapa", "TS", 676767);
		UserDto userDto2 = new UserDto("geetha@", "Geetha", GenderDto.FEMALE, 9877, 22, "jlkjgrsdht", null, Role.ROLE_CUSTOMER, addressDto2);
		this.userList.add(userDto);
		this.userList.add(userDto1);
		this.userList.add(userDto2);
		
	}
	
	@Test
	void createNewUserTest() throws Exception
	{

		AddressDto addressDto = new AddressDto("12-3","Sri", "kadapa", "AP", 676767);
		UserDto userDto1 = new UserDto("rahul@", "Rahul", GenderDto.MALE, 9877, 22, "12345", null, Role.ROLE_CUSTOMER,  addressDto);
		when(userService.addNewUser(any(UserDto.class))).thenAnswer((invocation) -> invocation.getArgument(0));

		UserDto check = userService.addNewUser(userDto1);
		assertEquals(userDto1.getEmailId(),check.getEmailId());
		assertEquals(userDto1.getName(),check.getName());
//		String userDto;
//		mockMvc.perform(post("/api/v2/user/")
//		        .contentType("application/json")
//		        .content(objectMapper.writeValueAsString(userDto1)))
//		        .andExpect(status().isOk())
//		        .andExpect(jsonPath("$.emailId", is(userDto1.getEmailId())))
//		        .andExpect(jsonPath("$.name", is(userDto1.getName())))
//		        .andExpect(jsonPath("$.age", is(userDto1.getAge())));

	}
	
	@Test
	void shouldReturn500WhenCreateNewUserWithoutEmailId() throws Exception
	{
		AddressDto addressDto = new AddressDto("12-4","Sri", "kadapa", "AP", 676767);
		UserDto userDto = new UserDto("rahul@", "Rahul", GenderDto.MALE, 9877, 22, "12345", null, Role.ROLE_CUSTOMER,  addressDto);
		when(userService.addNewUser(any(UserDto.class))).thenThrow(NullPointerException.class);
		mockMvc.perform(post("/api/v2/user/")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(userDto)))
		        .andExpect(status().isInternalServerError());
		
	}
	
	@Test
	void shouldGetUserByEmailIdTest() throws Exception{
		AddressDto addressDto = new AddressDto("12-8","Sri", "kadapa", "AP", 676767);
		UserDto userDto = new UserDto("rahul@", "Rahul", GenderDto.MALE, 9877, 22, "12345", null, Role.ROLE_CUSTOMER,  addressDto);
		final String emailId = "rahul@";
		
		when(userService.getUserById(emailId)).thenReturn(userDto);
		
		this.mockMvc.perform(get("/api/v2/user/{emailId}",emailId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.emailId", is(userDto.getEmailId())))
	        .andExpect(jsonPath("$.name", is(userDto.getName())));
	}
	
	@Test
	void shouldReturn404WhenGetUserByEmailIdTest() throws Exception{
		final String emailId = "rahul";
		
		when(userService.getUserById(emailId)).thenThrow(ResourceNotFoundException.class);
		
		this.mockMvc.perform(get("/api/v2/user/{emailId}",emailId))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldUpdateUser() throws Exception{
		AddressDto addressDto = new AddressDto("12-8","Sri", "kadapa", "AP", 676767);
		UserDto userDto = new UserDto("rahul@", "Rahul", GenderDto.MALE, 9877, 22, "12345", null, Role.ROLE_CUSTOMER,  addressDto);
		String emailId = "rahul@";

		UserDataDto userData = new UserDataDto("Rahul",9877,22);

		when(userService.getUserById(emailId)).thenReturn(userDto);
		when(userService.updateUser(emailId, userData)).thenReturn(userData);
		UserDataDto userDto1 = userService.updateUser(emailId,userData);
		mockMvc.perform(patch("/api/v2/user/{emailId}",emailId)
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(userData)))
		        .andExpect(status().isOk());
	}


	

}
