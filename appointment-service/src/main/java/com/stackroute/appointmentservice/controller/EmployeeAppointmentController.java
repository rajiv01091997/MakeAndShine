package com.stackroute.appointmentservice.controller;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.appointmentservice.dto.EmployeeAppointmentDto;
import com.stackroute.appointmentservice.interfaces.EmployeeAppointmentService;

@RestController
@RequestMapping("/appointment")
public class EmployeeAppointmentController {
    
	@Autowired
	private EmployeeAppointmentService employeeService;      //Bean for service class to call method implemented in service layer


	@GetMapping("/{serviceId}")
	@Operation(summary = "Get appointment details by  service ID",description = "To get particular appointment details by service Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202",description = "Will fetch particular appointment by its service ID"),
			@ApiResponse(responseCode = "400", description = "No such ID found..")
	})

	public ResponseEntity<List<EmployeeAppointmentDto>> getAppointmentDetailByServiceId(@PathVariable long serviceId)
	{
	  return new ResponseEntity<List<EmployeeAppointmentDto>>(employeeService.getAppointmentDetailByServiceId(serviceId),HttpStatus.OK);	
	}

   @GetMapping("/{employeeEmailId}")
   @Operation(summary = " Employee history for appointment ", description = "To get all history appointment details by employee email ID")
   @ApiResponses(value = {
		  @ApiResponse(responseCode= "202",description = "Will return all appointments created by particular employee"),
		   @ApiResponse(responseCode= "400",description = "No appointment have been created on this account yet..")
   })
	public ResponseEntity<List<EmployeeAppointmentDto>> getAppointmentDetailByEmployeeEmailId(String employeeEmailId)
	{
	   return new ResponseEntity<List<EmployeeAppointmentDto>>(employeeService.getAppointmentDetailByEmployeeEmailId(employeeEmailId),HttpStatus.OK);
	}
   
   @GetMapping("/appointment/{employeeEmailId}/{appointmentDate}")
   @Operation(summary = "Filter by appointment Date and employee email ID",description = "To get all appointment by filtering appointmentDate and employee email_Id")
   @ApiResponses(value = {
		   @ApiResponse(responseCode = "202",description = "Will return list of appointments by filtering appointment date and employee email_ID"),
		   @ApiResponse(responseCode = "400", description = "Oops..No appointment book for the given input..")
   })
   public ResponseEntity<List<EmployeeAppointmentDto>> getAppointmentDetailByEmployeeEmailIdAndAppointmentDate(String employeeEmailId,String appointmentDate)
   {
	   LocalDate date = LocalDate.parse(appointmentDate);
	   return new ResponseEntity<List<EmployeeAppointmentDto>>(employeeService.getAppointmentDetailByEmployeeEmailIdAndAppointmentDate(employeeEmailId, date),HttpStatus.OK);
   }
   
   @GetMapping("/getAppointments")
   @Operation(summary = "Get All appointments",description = "To get all booked appointments from booking slots")
   @ApiResponse(responseCode = "202",description = "Will return all booked appointments")
	public ResponseEntity<List<EmployeeAppointmentDto>> getAllAppointmentDetails()
	{
	   return new ResponseEntity<List<EmployeeAppointmentDto>>(employeeService.getAllAppointmentDetails(),HttpStatus.OK);
	}
  
  @GetMapping("/{ServiceId}/{appointmentDate}")
  @Operation(summary = "Filter by appointment Date and service_ID",description = "To get all appointment by filtering appointmentDate and service_Id")
  @ApiResponses(value = {
		  @ApiResponse(responseCode = "202",description = "Will return list of appointments by filtering appointment date and employee service_Id"),
		  @ApiResponse(responseCode = "400", description = "Oops..No appointment book for the given input..")
  })
  public ResponseEntity<List<EmployeeAppointmentDto>> getAppointmentDetailByServiceIdAndAppointmentDate(long serviceId,String appointmentDate)
  {
	  LocalDate date = LocalDate.parse(appointmentDate);

	  return new ResponseEntity<List<EmployeeAppointmentDto>>(employeeService.getAppointmentDetailByServiceIdAndAppointmentDate(serviceId, date),HttpStatus.OK);
  }
}
