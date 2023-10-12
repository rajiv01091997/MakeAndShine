package com.stackroute.appointmentservice.entity;


import java.time.LocalDate;
import java.time.LocalTime;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="employeeAppointment")
public class EmployeeAppointment {
         
	@Id
	private long appointmentId;
	private String  employeeEmailId;
	private long serviceId;
//	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate appointmentDate;
	private LocalTime appointmentStartTime;
	private LocalTime appointmentEndTime;
	private Boolean isAvailable;
	


}
