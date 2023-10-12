package com.stackroute.dto;

import com.stackroute.constants.Gender;
import com.stackroute.constants.ServiceCategory;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalonServiceDto {

	@Id
	private Long serviceid;
	private ServiceCategory serviceCategory;
	private String serviceName;
	private String serviceDescription;
	private double cost;
	private Gender gender;
	private byte[] serviceImage;
	//@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	//@JsonFormat(pattern="HH:mm")
	private String time;

}
