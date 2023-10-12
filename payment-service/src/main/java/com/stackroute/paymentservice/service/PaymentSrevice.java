package com.stackroute.paymentservice.service;


import java.util.Optional;

import com.stackroute.paymentservice.entity.PaymentInfoModel;
import com.stackroute.paymentservice.entity.PaymentRequest;
import com.stackroute.paymentservice.entity.PaymentResponse;

public interface PaymentSrevice { 
	
public PaymentResponse savePaymentRequest(PaymentRequest paymentRequest);

public Optional<PaymentInfoModel> getPayment(int id);

public Optional<PaymentInfoModel> updatePayment(int id, String status);
	


}
