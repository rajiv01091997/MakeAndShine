package com.stackroute.feedbackservice.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stackroute.feedbackservice.dto.FeedBackDetailsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import com.stackroute.feedbackservice.entity.FeedBackService;
import com.stackroute.feedbackservice.repository.FeedBackServiceRepository;

@SpringBootTest
public class FeedBackServiceServiceTest {

	@MockBean
	private FeedBackServiceRepository feedBackRepository;
	
	@Autowired
	private IFeedBackService iFeedBackService;
	

	@Test
	void  CreateNewFeedBack() {

		FeedBackService fakeObj2 = new FeedBackService(1,"groom package","hemo@gmail.com","hema",5);
		when(feedBackRepository.save(fakeObj2)).thenReturn(fakeObj2);
		FeedBackServiceDto feedBackServiceDto = iFeedBackService.convertToDto(fakeObj2);
		FeedBackServiceDto feedBackServiceDto2 = iFeedBackService.addFeedBack(feedBackServiceDto);
		assertEquals(feedBackServiceDto2.getAppointmentId(),fakeObj2.getAppointmentId());	
	}
	
	@Test
    void GetFeedBackServiceDetails() {

		FeedBackService fakeObj1 = new FeedBackService(1,"groom package","hemo@gmail.com","hema",5);
		FeedBackService fakeObj2 = new FeedBackService(2,"groom package","hemo@gmail.com","hema",5);

      when(feedBackRepository.findAll()).thenReturn(Stream.of(fakeObj1,fakeObj2).collect(Collectors.toList()));
		
		List<FeedBackServiceDto> feedBackServiceDtos = iFeedBackService.getFeedBackService();
		
		assertEquals(feedBackServiceDtos.size(), 2);
   }
	
	@Test
	void updateFeedbackService()
	{
		FeedBackService fakeObj1 = new FeedBackService(1,"jyoti@gmail.com","jyoti","best salon must visit",5);
		Long appointmentId=1L;
		when(feedBackRepository.findById(appointmentId)).thenReturn(Optional.of(fakeObj1));
		FeedBackDetailsDto feedBackDetailsDto = new FeedBackDetailsDto(1L,"good salon must visit here",5);
		feedBackDetailsDto.setRating(4);
		when(iFeedBackService.updateFeedBackService(feedBackDetailsDto,appointmentId)).thenReturn(feedBackDetailsDto);
		assertEquals(feedBackDetailsDto.getRating(), fakeObj1.getRating());

	}
	
	
}