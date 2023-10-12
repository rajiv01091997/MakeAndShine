package com.stackroute.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.paymentservice.entity.PaymentRequest;



@Repository
public interface OrderRepository extends MongoRepository<PaymentRequest, String>{

}
