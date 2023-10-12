package com.stackroute.feedbackservice.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.feedbackservice.dto.FeedBackDetailsDto;
import com.stackroute.feedbackservice.dto.FeedBackServiceDto;
import com.stackroute.feedbackservice.service.IFeedBackService;

@RestController
@RequestMapping("/api/v6")
public class FeedBackController {

	     @Autowired
	     private IFeedBackService iFeedBackService;
	     
	  @PostMapping("/feedback")
	  @Operation(summary = "create feedback", description = "To give feedback for particular service")
	  @ApiResponses(value = {
			  @ApiResponse(responseCode = "200",description = "feedback is create successfully")
	  })
	  public ResponseEntity<FeedBackServiceDto> addFeedBack(@Valid @RequestBody  FeedBackServiceDto feedBackServiceDto) 
	  {
		  return new ResponseEntity<FeedBackServiceDto>(iFeedBackService.addFeedBack(feedBackServiceDto),HttpStatus.OK);
		  
	  }
	  
	  @GetMapping("/feedback")
	  @Operation(summary = "get feedback services",description = "To get all feedback service ")
	  @ApiResponses(value = {
			  @ApiResponse(responseCode = "202",description = "Will return list of feedback  service"),
			  @ApiResponse(responseCode = "400", description = "Oops..No feedback present...")
	  })
	  public ResponseEntity<List<FeedBackServiceDto>> getFeedBackService()
	  {
		  return new ResponseEntity<>(iFeedBackService.getFeedBackService(),HttpStatus.OK); 
		  
	  }
	  	  
	  @PatchMapping("/update/{appointmentId}")
	  @Operation(summary = "update feedback ",description = "To update comment and rating by using appointmentId")
	  @ApiResponses(value = {
			  @ApiResponse(responseCode = "200",description = "will return update feedback by particular appointment_Id"),
			  @ApiResponse(responseCode = "400",description = "You haven't update feedback because invalid appointment_Id")
	  })
	  public ResponseEntity<FeedBackDetailsDto> updateFeedBackService(@RequestBody FeedBackDetailsDto feedBackServiceDto,@PathVariable  long appointmentId){
		  
		  return new ResponseEntity<FeedBackDetailsDto>(iFeedBackService.updateFeedBackService(feedBackServiceDto,appointmentId),HttpStatus.OK);
		  
	  } 
	
}	  
	  
