package com.stackroute.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {
	private String houseNo;
	private String streetName;
	private String cityName;
	private String stateName;
	private int pinCode;
	
}
