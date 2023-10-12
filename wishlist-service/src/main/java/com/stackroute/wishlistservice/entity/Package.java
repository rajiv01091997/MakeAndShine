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

public class Package {
   
	public long packageId;
	
	public String packageName;
	
	public double packagePrice;
	
	public  byte[] packagephoto ;
	

	


	

	
	
	

}
