package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.entity.Salon;

public interface SalonRepository extends MongoRepository<Salon, Long>{

}
