package com.stackroute.wishlistservice.dto;

import java.util.List;

import com.stackroute.wishlistservice.entity.Package;


import com.stackroute.wishlistservice.entity.Services;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;

@Data
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WishListDto {
	
       @Indexed(unique = true)
		private String emailid;

		private List<Services> services;
		
		private List<Package> packages;
		
		private double amount;
		
		
		
	

		
		
		


		
		

	



		



		
		
		
		
		
		
		
		
		}

		
		
		

		

