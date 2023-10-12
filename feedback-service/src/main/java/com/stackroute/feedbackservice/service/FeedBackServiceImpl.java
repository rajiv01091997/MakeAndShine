package com.stackroute.feedbackservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.feedbackservice.dto.FeedBackDetailsDto;
import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import com.stackroute.feedbackservice.entity.FeedBackService;
import com.stackroute.feedbackservice.exception.IdNotFoundException;
import com.stackroute.feedbackservice.repository.FeedBackServiceRepository;

@Service
public  class FeedBackServiceImpl  implements IFeedBackService{

	@Autowired
	private FeedBackServiceRepository feedBackRepository;


	/*
	 * @Description : This method is used to create a new feedback and stores the data into database
	 * @Params : This method contains one parameter of type FeedBackServiceDto
	 * @Author : jyoti
	 */
	@Override
	public FeedBackServiceDto addFeedBack(FeedBackServiceDto feedBackServiceDto) {

		FeedBackService feedBackService = convertToEntity(feedBackServiceDto);
		feedBackService.setAppointmentId(SequenceGeneratorService.generateSequence(FeedBackService.SEQUENCE_NAME));
		//FeedBackService save = feedBackRepository.save(feedBackService);
		feedBackRepository.save(feedBackService);
		feedBackServiceDto.setAppointmentId(feedBackService.getAppointmentId());
		return feedBackServiceDto;
	}

	/*
	 * @Description : This method is used to get all the feedback from the database
	 * @Author : jyoti
	 */
	public List<FeedBackServiceDto> getFeedBackService() {
	 
		List<FeedBackService> list = feedBackRepository.findAll();
		List<FeedBackServiceDto>  feedBackServiceDtos = new ArrayList<>();
		for (FeedBackService feedBackService : list) {
			
			FeedBackServiceDto feedDto = convertToDto(feedBackService);
			feedBackServiceDtos.add(feedDto);
		}		
	      return feedBackServiceDtos;
	}

	/*
	 * @Description : This method is used to Update a comment  and rate of feedback stores the data into database
	 * @Params : This method contains two parameters one of the FeedBackDetailsDto and other of type Long
	 * @Author : jyoti,
	 */
	@Override
	public FeedBackDetailsDto updateFeedBackService(FeedBackDetailsDto feedBackDetailsDto, long appointmentId) {

		Optional<FeedBackService> feedBackServiceOptional= feedBackRepository.findById(appointmentId);
		
		if(feedBackServiceOptional.isEmpty()) {
			throw new IdNotFoundException("No such type id is present...");
		}


		feedBackServiceOptional.get().setComment(feedBackDetailsDto.getComment());
		feedBackServiceOptional.get().setRating(feedBackDetailsDto.getRating());
		
		feedBackRepository.save(feedBackServiceOptional.get());
		feedBackDetailsDto.setAppointmentId(feedBackServiceOptional.get().getAppointmentId());
		return feedBackDetailsDto;
	}

	/*
	 * @Description : This method is used to convert FeedBackServiceDto to FeedBackService by using ModelMapper class
	 * @Params : This method contains one parameter of type FeedBackServiceDto
	 * @Author : jyoti
	 */
	public FeedBackService convertToEntity(FeedBackServiceDto feedBackServiceDto)
	{
		
		FeedBackService feedBackService =  new FeedBackService();
		feedBackService = new ModelMapper().map(feedBackServiceDto, FeedBackService.class);
		return feedBackService;
	}

	/*
	 * @Description : This method is used to convert FeedBackService to FeedBackServiceDto by using ModelMapper class
	 * @Params : This method contains one parameter of type FeedBackService
	 * @Author : jyoti
	 */
	public FeedBackServiceDto convertToDto(FeedBackService feedBackService)
	{
		FeedBackServiceDto feedBackServiceDto = new FeedBackServiceDto();
		feedBackServiceDto =  new ModelMapper().map(feedBackService, FeedBackServiceDto.class);
		return feedBackServiceDto;
		
	}

	
	



	
	
	







	
}
