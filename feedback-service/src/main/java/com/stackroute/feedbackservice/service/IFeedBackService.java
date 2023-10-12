package com.stackroute.feedbackservice.service;

import java.util.List;

import com.stackroute.feedbackservice.dto.FeedBackDetailsDto;
import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import com.stackroute.feedbackservice.entity.FeedBackService;

public interface IFeedBackService {

	FeedBackServiceDto addFeedBack(FeedBackServiceDto feedBackServiceDto);   //To create new feedback in service

	List<FeedBackServiceDto> getFeedBackService();         //To get list of all feedback details

	FeedBackDetailsDto updateFeedBackService(FeedBackDetailsDto feedBackDetailsDto, long appointmentId);    // To update the feedback by using particular appointmentId

	FeedBackServiceDto convertToDto(FeedBackService fakeObj2);        //This  method converting the FeedBackService class to Dto class
}