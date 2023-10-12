package com.stackroute.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	private String houseNo;
	private String streetName;
	private String cityName;
	private String stateName;
	private int pinCode;
	
}
