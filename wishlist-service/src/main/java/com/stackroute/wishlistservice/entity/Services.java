package com.stackroute.wishlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Services {

 public long serviceId ;
 
 public String serviceName;
 
 public double servicePrice;
 
 public byte[] servicePhoto;
 

	
	
	
}
