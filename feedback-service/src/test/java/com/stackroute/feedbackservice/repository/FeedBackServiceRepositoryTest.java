package com.stackroute.feedbackservice.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.stackroute.feedbackservice.entity.FeedBackService;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class FeedBackServiceRepositoryTest {

	
	@Mock
	 FeedBackServiceRepository feedBackRepository;
	
	@Test
	void getFeedBackServiceDetails() {


		FeedBackService fakeObj1 = new FeedBackService(1,"jyoti@gmail.com","jyoti","best salon must visit",5);
		FeedBackService fakeObj2 = new FeedBackService(2,"dipali@gmail.com","dipali","bad salon don't visit",2);
		List<FeedBackService> feedBackServices = new ArrayList<>();
		feedBackServices.add(fakeObj1);
		feedBackServices.add(fakeObj2);
		when(feedBackRepository.findAll()).thenReturn(feedBackServices);
		List<FeedBackService> feedBackServices2 = feedBackRepository.findAll();
		assertEquals(feedBackServices2.size(),2);
		
	}
	/*
	 *  positive 
	 * */
	@Test
	void  createNewFeedBackService() {

		FeedBackService fakeObj1 = new FeedBackService(1,"groom package","hemo@gmail.com","hema",5);
        when(feedBackRepository.save(fakeObj1)).thenReturn(fakeObj1);
		FeedBackService feedBackService = feedBackRepository.save(fakeObj1);
        assertEquals(feedBackService.getAppointmentId(), fakeObj1.getAppointmentId());
        assertEquals(feedBackService.getCustomerEmailId(), fakeObj1.getCustomerEmailId());

	}
	
	/*
	 *  negative 
	 * */
	@Test
	void shouldReturn500whencreateNewFeedBackService() {

		FeedBackService fakeObj1 = new FeedBackService(1,"groom package","hemo@gmail.com","hema",5);
        when(feedBackRepository.save(fakeObj1)).thenReturn(null);
		FeedBackService feedBackService = feedBackRepository.save(fakeObj1);
		assertNull(feedBackService);
	}
	
	
}
