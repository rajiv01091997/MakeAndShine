package com.stackroute.appointmentservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAppointmentDto{

	
	
	private long appointmentId;
	private String  employeeEmailId;
	private long serviceId;
//	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate appointmentDate;
	private LocalTime appointmentStartTime;
	private LocalTime appointmentEndTime;
	private Boolean isAvailable;


}
