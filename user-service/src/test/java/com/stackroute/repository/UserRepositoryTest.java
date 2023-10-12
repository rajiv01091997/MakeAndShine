package com.stackroute.repository;

import com.stackroute.dto.UserDto;
import com.stackroute.model.Address;
import com.stackroute.model.Gender;
import com.stackroute.model.Role;
import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {

    @MockBean
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetAllUsers()
    {
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
        List<User> users = userRepository.findAll();
        assertEquals(userList.size(),users.size());
    }

    @Test
    void createNewUserTest()
    {
        Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
        User user = new User("rahul@123", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);

        when(userRepository.save(user)).thenReturn(user);

        User user1 = userRepository.save(user);

        assertEquals(user.getEmailId(),user1.getEmailId());
        assertEquals(user.getName(), user1.getName());
    }

    @Test
    void createNewUserWhenNoUserIsPassedTest()
    {
        User user = new User();
        when(userRepository.save(user)).thenReturn(null);
        assertNull(userRepository.save(user));
    }

    @Test
    void getUserByIdTest()
    {
        Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
        User user = new User("rahul@123", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);

        String emailId = "rahul@";
        when(userRepository.findById(emailId)).thenReturn(Optional.of(user));

        User checkUser = userRepository.findById(emailId).get();

        assertEquals(user.getEmailId(), checkUser.getEmailId());
        assertEquals(user.getName(), checkUser.getName());
        assertEquals(user.getAge(), checkUser.getAge());
    }

    @Test
    void getUserByIdWhenNoUserIsPresent()
    {
        String emailId = "rahul@";
        when(userRepository.findById(emailId)).thenReturn(null);
        assertNull(userRepository.findById(emailId));
    }

    @Test
    void updateUserTest(){
        Address address = new Address("12-8","Sri", "kadapa", "AP", 676767);
        User user = new User("rahul@123", "Rahul", Gender.MALE, 9877, 22, "12345", null,  Role.ROLE_CUSTOMER,address);
        user.setAge(26);
        when(userRepository.save(user)).thenReturn(user);
        User checkUser = userRepository.save(user);
        assertEquals(user.getEmailId(), checkUser.getEmailId());
        assertEquals(user.getName(), checkUser.getName());
        assertEquals(user.getAge(), checkUser.getAge());
    }

}
