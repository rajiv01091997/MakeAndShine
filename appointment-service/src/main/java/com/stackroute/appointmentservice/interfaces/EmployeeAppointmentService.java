package com.stackroute.appointmentservice.interfaces;

import java.time.LocalDate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.appointmentservice.dto.EmployeeAppointmentDto;


@Service
public interface EmployeeAppointmentService {

	  EmployeeAppointmentDto addAppointmentDto(EmployeeAppointmentDto employeeAppointmentDto);     //To create appointment by selecting particular service
	  
	  List<EmployeeAppointmentDto> getAppointmentDetailByServiceId(long serviceId);              //To get booked appointment for particular appointment by using service_Id
	  
	  List<EmployeeAppointmentDto> getAppointmentDetailByEmployeeEmailId(String employeeEmailId);   //To get all records appointment by employee email Id
	  
	  List<EmployeeAppointmentDto> getAppointmentDetailByEmployeeEmailIdAndAppointmentDate(String employeeEmailId,LocalDate appointmentDate);    //To get  booked appointment records by filtering appointment by appointment-date and employee emailId
	  
	  List<EmployeeAppointmentDto> getAllAppointmentDetails();   //To get all booked appointment records
	  
	  List<EmployeeAppointmentDto> getAppointmentDetailByServiceIdAndAppointmentDate(long serviceId,LocalDate appointmentDate);    //To get  booked appointment records by filtering appointment by appointment-date and service_Id
	  
	  EmployeeAppointmentDto updateAppointmentDetails(long appointmentId, EmployeeAppointmentDto employeeAppointmentDto);     // To update booked appointment by appointment_Id
	  
	  void cancelAppointmentDetails(long appointmentId);       // Method for cancel booked appointment
}
