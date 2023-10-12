package com.stackroute.appointmentservice.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.appointmentservice.controller.BookAppointmentController;
import com.stackroute.appointmentservice.dto.BookAppointmentDto;
import com.stackroute.appointmentservice.interfaces.BookAppointmentService;
import com.stackroute.appointmentservice.repository.BookAppointmentRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookAppointmentController.class)
@ActiveProfiles("test")
class AppointmentServiceControllerTest{
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 private BookAppointmentService bookService;
	 
	 @MockBean
	 private BookAppointmentRepository bookRepository;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 private List<BookAppointmentDto> bookList=new ArrayList<>();
	 
	 @BeforeEach
	 void setUp() {
		 
		 
		 BookAppointmentDto bookAppointmentDto1 = new BookAppointmentDto(101,111,"jyoti@gmail.com",null,null,null,"best salon",true,"dipali@gmail.com");
		 BookAppointmentDto bookAppointmentDto2 = new BookAppointmentDto(102,222,"pooja@gmail.com",null,null,null,"must visit",true,"bharat@gmail.com");
       
		 this.bookList.add(bookAppointmentDto1);
		 this.bookList.add(bookAppointmentDto2);
		 
	 }
	
	 @Test
	 void getBookingDetailsByAppointmentID() throws Exception {
		
		 BookAppointmentDto fakeDtoObj1 = new BookAppointmentDto(101,111,"jyoti@gmail.com",null,null,null,"best salon",true,"dipali@gmail.com");
		 
		 long appointmentId = 1;
		 when(bookService.getBookingDetailsByAppointmentID(appointmentId)).thenReturn(fakeDtoObj1);
		 this.mockMvc.perform(get("/api/booking/appointment/appointmentDetails/{appointmentId}",appointmentId)).andExpect(status().isOk())
		 .andExpect(jsonPath("$.comment", is(fakeDtoObj1.getComment())))
	        .andExpect(jsonPath("$.customerEmailId", is(fakeDtoObj1.getCustomerEmailId())));
	}
	
	 @Test
	 void createBookingAppointment() throws Exception{
		 BookAppointmentDto fakeDtoObj1 = new BookAppointmentDto(101,111,"jyoti@gmail.com",null,null,null,"best salon",true,"dipali@gmail.com");
         when(bookService.addBookingAppointment(fakeDtoObj1)).thenReturn(fakeDtoObj1);
         mockMvc.perform(post("/api/booking/appointment/")
 		        .contentType("application/json")
 		        .content(objectMapper.writeValueAsString(fakeDtoObj1)))
 		        .andExpect(status().isCreated());
		 
	 }
	 
	 @Test
	 void getBookingDetailsByCustomerEmailId() throws Exception{
		 
           String customerEmailId = "jyoti@gmail.com";
		 when(bookService.getBookingDetailsByCustomerEmailId(customerEmailId)).thenReturn(bookList);
		 this.mockMvc.perform(get("/api/booking/appointment/{customerEmailId}",customerEmailId)).andExpect(status().isOk())
		 .andExpect(jsonPath("$.size()", is(bookList.size())));
	 }
		 
		 
	 
	 
	
}