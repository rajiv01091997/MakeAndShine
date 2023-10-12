package com.stackroute.feedbackservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.feedbackservice.entity.FeedBackService;

@Repository
public interface FeedBackServiceRepository extends MongoRepository<FeedBackService, Long>{
     
	
}
