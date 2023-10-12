package com.stackroute.entity;

import com.stackroute.constants.Gender;
import com.stackroute.constants.ServiceCategory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;


@Document(collection = "OurService")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Salon {

	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private Long serviceid;
	private ServiceCategory serviceCategory;
	private String serviceName;
	private String serviceDescription;
	private double cost;
	private Gender gender;
	private byte[] serviceImage;
	//@JsonFormat(pattern="HH:mm")
	private LocalTime time;

}
	
