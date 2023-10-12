package com.stackroute.paymentservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.paymentservice.entity.PaymentInfoModel;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentInfoModel, Integer> {

}
