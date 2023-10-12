package com.stackroute.paymentservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.paymentservice.entity.PaymentInfoModel;
import com.stackroute.paymentservice.entity.PaymentRequest;
import com.stackroute.paymentservice.entity.PaymentResponse;
import com.stackroute.paymentservice.service.PaymentSrevice;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	public PaymentSrevice service;

	
	@RequestMapping(value="/api/v/payment",consumes = {"application/json"})
	@ResponseBody
	public PaymentResponse payAmount(@RequestBody PaymentRequest paymentRequest) {
		return service.savePaymentRequest(paymentRequest);
	}  
	

	@GetMapping("/api/v/payment/{id}")
	@ResponseBody
	public Optional<PaymentInfoModel> getPayments(@PathVariable int id) {
		return service.getPayment(id);
	}

	@PutMapping("api/v/payment/{id}")
	public Optional<PaymentInfoModel> updateOrder(@RequestBody String status, @PathVariable("id") int id){
		return service.updatePayment(id, status);
	}
	
	
}
