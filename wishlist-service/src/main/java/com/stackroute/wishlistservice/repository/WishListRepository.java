package com.stackroute.wishlistservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.wishlistservice.entity.WishList;
@Repository
public interface WishListRepository extends MongoRepository<WishList, String> {
	

}
