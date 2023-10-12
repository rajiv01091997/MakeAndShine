package com.stackroute.service.impl;

import java.util.*;

import com.stackroute.config.MQConfig;
import com.stackroute.dto.*;
import com.stackroute.model.Address;
import com.stackroute.model.Gender;
import com.stackroute.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.exceptions.ResourceAlreadyPresentException;
import com.stackroute.exceptions.ResourceNotFoundException;
import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private RabbitTemplate template;
	
	/*
	 * @Description : This method is used to create a new user and stores the data into database
	 * @Params : This method contains one parameter of type UserDto
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	@Override
	public UserDto addNewUser(UserDto userDto) {
		if(userDto.getEmailId().isEmpty())
		{
			throw new NullPointerException("EmailId is Mandatory");
		}
		if(userRepository.findById(userDto.getEmailId()).isPresent())
		{
			throw new ResourceAlreadyPresentException("Emaild is already registered : "+userDto.getEmailId());
		}
		LoginDto login = new LoginDto();
		login.setEmailId(userDto.getEmailId());
		login.setPassword(userDto.getPassword());
		if(userDto.getRoleDto()==Role.ROLE_CUSTOMER){
			Set<String> roles = new HashSet<>();
			roles.add("ROLE_CUSTOMER");
			login.setRole(roles);
		}
		else {
			Set<String> roles = new HashSet<>();
			roles.add("ROLE_EMPLOYEE");
			login.setRole(roles);
		}
		login.setUserName(userDto.getName());
		template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,login);
     	User user = convertToEntity(userDto);
		userRepository.save(user);
		return userDto;
	}

	/*
	 * @Description : This method is used to get all the users from the database
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		//System.out.println(users);
		List<UserDto> userDtos = new ArrayList<>();
		for(User user : users)
		{
			UserDto userDto = convertToDto(user);
			userDtos.add(userDto);
		}
		return userDtos;
	}

	/*
	 * @Description : This method is used to get the user details from database
	 * @Params : This method contains one parameter of type String emailId
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	@Override
	public UserDto getUserById(String emailId) {
		if(emailId.isEmpty())
		{
			throw new NullPointerException("EmailId is Mandatory");
		}
		Optional<User> customer= userRepository.findById(emailId);
		if(customer.isEmpty())
		{
			throw new ResourceNotFoundException("No Id found with this email Id : "+emailId);
		}
		UserDto userDto = convertToDto(customer.get());
		return userDto;
	}
	
	/*
	 * @Description : This method is used to Update a new user and stores the data into database
	 * @Params : This method contains two parameters one of type String and other of type UserDto
	 * @Author : Dinesh",
    "pinCode": 876111
	 * @Created Date : 19th November 2022
	 */

	@Override
	public UserDataDto updateUser(String emailid, UserDataDto userData) {
		Optional<User> user = userRepository.findById(emailid);
		if(user.isEmpty())
		{
			throw new ResourceNotFoundException("No Id found with this email Id to update : "+emailid);
		}
		user.get().setAge(userData.getAge());
		user.get().setMobile(userData.getMobile());
		user.get().setName(userData.getName());
		//user.get().setPassword(userData.getPassword());
		userRepository.save(user.get());
		return userData;
	}
	
	/*
	 * @Description : This method is used to convert UserDto to User by using ModelMapper class
	 * @Params : This method contains one parameter of type UserDto
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	public User convertToEntity(UserDto userDto)
	{
		User user = new User();
		user = new ModelMapper().map(userDto, User.class);
		if(userRepository.findById(userDto.getEmailId()).isPresent())
			user.setUserImage(userRepository.findById(userDto.getEmailId()).get().getUserImage());
		return user;
	}
	
	/*
	 * @Description : This method is used to convert User to UserDto by using ModelMapper class
	 * @Params : This method contains one parameter of type User
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	public UserDto convertToDto(User user)
	{
		UserDto userDto = new UserDto();
		userDto = new ModelMapper().map(user, UserDto.class);
		AddressDto addressDto = new AddressDto();
		addressDto.setPinCode(user.getAddress().getPinCode());
		addressDto.setStateName(user.getAddress().getStateName());
		addressDto.setStreetName(user.getAddress().getStreetName());
		addressDto.setCityName(user.getAddress().getCityName());
		addressDto.setHouseNo(user.getAddress().getHouseNo());
		userDto.setAddressDto(addressDto);
		userDto.setRoleDto(user.getRole());
		if(userRepository.findById(user.getEmailId()).isPresent())
			userDto.setUserImage(userRepository.findById(user.getEmailId()).get().getUserImage());
		//userDto.setGenderDto(user.getGender());
		if(user.getGender()== Gender.MALE)
			userDto.setGenderDto(GenderDto.MALE);
		else
			userDto.setGenderDto(GenderDto.FEMALE);

		return userDto;
	}

	/*
	 * @Description : This method is used to change user Image
	 * @Params : It contains two parameters of type String and byte array
	 * @Author : Dinesh
	 * @Created Date : 19th November 2022
	 */
	@Override
	public String updateUserImage(String emailId,byte[] image) {
		Optional<User> user = userRepository.findById(emailId);
		if(user.isEmpty())
		{
			throw new ResourceNotFoundException("No Id found with this email Id to update : "+emailId);
		}
		user.get().setUserImage(image);
		userRepository.save(user.get());
		return "Uploaded sucessfully";
	}

	@Override
	public AddressDto updateUserAddress(String emailId, AddressDto addressDto) {
		Optional<User> user = userRepository.findById(emailId);
		if(user.isEmpty())
		{
			throw new ResourceNotFoundException("No Id found with this email Id to update : "+emailId);
		}
		Address address = new Address();
		address.setCityName(addressDto.getCityName());
		address.setPinCode(addressDto.getPinCode());
		address.setStreetName(addressDto.getStreetName());
		address.setStateName(addressDto.getStateName());
		address.setHouseNo(addressDto.getHouseNo());
		user.get().setAddress(address);
		userRepository.save(user.get());
		return addressDto;
	}


}
