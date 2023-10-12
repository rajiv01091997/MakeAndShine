package com.stackroute.appointmentservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="bookedslot")
public class BookAppointment {

	@Transient
	public static final String SEQUENCE_NAME="appointment_sequence";

	
	@Id
	private long appointmentId;
	
	
	private long serviceId;
	
	private String customerEmailId;
	
	
	private LocalDate bookSlotDate;
	
	
	private LocalTime bookSlotStartTime;
	
	
	private LocalTime bookSlotEndTime;
	
	private String comment;
	
	private Boolean isCancelled;
	
	private String employeeEmailId;
	


}
