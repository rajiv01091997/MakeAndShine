package com.stackroute.feedbackservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stackroute.feedbackservice.repository.FeedBackServiceRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import com.stackroute.feedbackservice.service.IFeedBackService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FeedBackController.class)
@ActiveProfiles("test")
  class FeedBackServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IFeedBackService iFeedBackService;
	
	@MockBean
	private FeedBackServiceRepository feedBackRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private List<FeedBackServiceDto> FeedBackServiceDto;

	@BeforeEach
	public void getFeedBackService() throws Exception
	{
		 this.FeedBackServiceDto =  new ArrayList<>();

		FeedBackServiceDto fakeDtoObj1 = new FeedBackServiceDto(1,"groom package","hemo@gmail.com","hema",5);
		FeedBackServiceDto fakeDtoObj2 = new FeedBackServiceDto(2,"groom package","hemo@gmail.com","hema",5);
      
		this.FeedBackServiceDto.add(fakeDtoObj1);
		this.FeedBackServiceDto.add(fakeDtoObj2);
 
	}	

	@Test
	public void createFeedBackService() throws Exception{

		FeedBackServiceDto fakeDtoObj1 = new FeedBackServiceDto(1,"groom package","hemo@gmail.com","hema",5);
		
        when(iFeedBackService.addFeedBack(fakeDtoObj1)).thenReturn(fakeDtoObj1);
        
        mockMvc.perform(post("/api/v2/feedback")
 		        .contentType("application/json")
 		        .content(objectMapper.writeValueAsString(fakeDtoObj1)))
 		        .andExpect(status().isOk());
	}

	@Test
	void shouldReturn500createAppointment() throws Exception
	{
		FeedBackServiceDto fakeDtoObj1 = new FeedBackServiceDto(1,"groom package","hemo@gmail.com","hema",5);
		when(iFeedBackService.addFeedBack(ArgumentMatchers.any(FeedBackServiceDto.class))).thenThrow(NullPointerException.class);
		mockMvc.perform(post("/api/v2/feedback")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(fakeDtoObj1)))
				        .andExpect(status().isOk());
	}

		@Test
		void shouldFetchAllUsers() throws Exception{

			FeedBackServiceDto fakeObj1 = new FeedBackServiceDto(1,"jyoti@gmail.com","jyoti","best salon must visit",5);
			FeedBackServiceDto fakeObj2 = new FeedBackServiceDto(2,"dipali@gmail.com","dipali","bad salon don't visit",2);
			List<FeedBackServiceDto> feedBackServicesDto = new ArrayList<>();
			feedBackServicesDto.add(fakeObj1);
			feedBackServicesDto.add(fakeObj2);
			when(iFeedBackService.getFeedBackService()).thenReturn(feedBackServicesDto);
			
			this.mockMvc.perform(get("/api/v2/feedback"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(feedBackServicesDto.size())));
	}

	//convert entity to Dto
//	 public FeedBackServiceDto convertToDto(FeedBackService feedBackService){
//		FeedBackServiceDto feedBackServiceDto =new ModelMapper().map(feedBackService, FeedBackServiceDto.class);
//        return feedBackServiceDto;
//	 }
//   //convert Dto to entity
//	public FeedBackService convertToEntity(FeedBackServiceDto feedBackServiceDto){
//
//		FeedBackService feedBackService = new ModelMapper().map(feedBackServiceDto,FeedBackService.class);
//		return feedBackService;
//	}


}