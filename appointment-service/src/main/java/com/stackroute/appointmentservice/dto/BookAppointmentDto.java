package com.stackroute.appointmentservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAppointmentDto {
	
	
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
