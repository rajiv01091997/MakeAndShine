package com.stackroute.packageservice.repository;

import com.stackroute.packageservice.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;


@Repository

public interface PackageRepository extends MongoRepository<Package, Long>{

	

}