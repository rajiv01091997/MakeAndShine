package com.stackroute.controller;


import com.stackroute.exceptions.AuthenticationFailureException;
import com.stackroute.model.Emailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.service.EmailService;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v3/")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<String> sendMail(@Valid @RequestBody Emailer emailer) throws MessagingException {

		if(!emailer.getSenderEmail().equals("rajiv01091997@gmail.com"))
			throw new AuthenticationFailureException("Enter correct sender Email address");


		return new ResponseEntity<String>(emailService.sendMail(emailer),HttpStatus.OK);
	}
	
}
