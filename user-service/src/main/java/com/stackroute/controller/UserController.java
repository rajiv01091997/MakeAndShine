package com.stackroute.controller;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.dto.AddressDto;
import com.stackroute.dto.UserDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.dto.UserDto;
import com.stackroute.service.IUserService;


@RestController
@RequestMapping("/api/v2/user")
public class UserController {
	
	@Autowired
	IUserService userService;

	@PostMapping("/")
	@Operation(summary = "add new user in to database", description = "Creates a new user and adds the user to database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Will add new user"),
			@ApiResponse(responseCode = "406" , description = "Email Id is already registered...")
	})
	public ResponseEntity addNewUser(@RequestParam String userDto,@RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
		//@RequestParam(value = "file",required = false) MultipartFile file,
        //@RequestParam(value = "file",required = false) MultipartFile file
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		UserDto userDto1 = objectMapper.readValue(userDto,UserDto.class);
		userDto1.setUserImage(image);
		return new ResponseEntity<>(userService.addNewUser(userDto1), HttpStatus.OK);
	}

	@GetMapping("/get")

	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{emailId}")
	@Operation(summary = "Get user details by email Id", description = "To get particular user details by email Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Will fetch particular user by his/her email id"),
			@ApiResponse(responseCode = "404" , description = "Email Id not found")
	})
	public ResponseEntity<UserDto> getUserByEmailId(@PathVariable String emailId)
	{
		return new ResponseEntity<>(userService.getUserById(emailId), HttpStatus.OK);
	}
	
	@PatchMapping("/{emailId}")
	public ResponseEntity<UserDataDto> updateUser(@PathVariable String emailId, @RequestBody UserDataDto userData)
	{
		return new ResponseEntity<>(userService.updateUser(emailId, userData), HttpStatus.OK);
	}

	@PatchMapping("/image/{emailId}")
	public ResponseEntity updateUserImage(@PathVariable String emailId, @RequestPart("file") MultipartFile file)
	{
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
		userService.updateUserImage(emailId,image);
		return new ResponseEntity("Uploaded sucessfully",HttpStatus.OK);
	}

	@PatchMapping("/address/{emailId}")
	public ResponseEntity<AddressDto> updateUserAddress(@PathVariable String emailId, @RequestBody AddressDto addressDto)
	{
		return new ResponseEntity<>(userService.updateUserAddress(emailId,addressDto),HttpStatus.OK);
	}

}
