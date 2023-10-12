package com.stackroute.paymentservice.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PaymentRequest {
	
	@Id
	String email;
	String phoneNumber;
	BigInteger amount;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	

}
