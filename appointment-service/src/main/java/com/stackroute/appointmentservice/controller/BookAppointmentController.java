package com.stackroute.appointmentservice.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.stackroute.appointmentservice.dto.AppointmentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.appointmentservice.dto.BookAppointmentDto;
import com.stackroute.appointmentservice.interfaces.BookAppointmentService;



@RestController
@RequestMapping("/api/booking/appointment")
public class BookAppointmentController {


	@Autowired
	private BookAppointmentService bookService;

	
	@PostMapping("/")
	@Operation(summary = "Book appointments", description = "To book appointment for particular service")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "appointment has been booked"),
			@ApiResponse(responseCode = "400", description = "appointment has been already booking by another user for this date...")
	})
	public ResponseEntity<BookAppointmentDto> addBookingAppointment( @Valid @RequestBody AppointmentDto bookAppointmentDto)
	{
		LocalDate date=LocalDate.parse(bookAppointmentDto.getBookSlotDate());
		BookAppointmentDto bookAppointmentDto1 = new BookAppointmentDto();
		bookAppointmentDto1.setBookSlotDate(date);
		bookAppointmentDto1.setBookSlotEndTime(bookAppointmentDto.getBookSlotEndTime());
		bookAppointmentDto1.setBookSlotStartTime(bookAppointmentDto.getBookSlotStartTime());
		bookAppointmentDto1.setComment(bookAppointmentDto.getComment());
		bookAppointmentDto1.setServiceId(bookAppointmentDto.getServiceId());
		bookAppointmentDto1.setCustomerEmailId(bookAppointmentDto.getCustomerEmailId());
        bookAppointmentDto1.setEmployeeEmailId(bookAppointmentDto.getEmployeeEmailId());
		bookAppointmentDto1.setIsCancelled(false);

		return new ResponseEntity<BookAppointmentDto>(bookService.addBookingAppointment(bookAppointmentDto1),HttpStatus.CREATED);
	}
	

	@GetMapping("/appointment_details/appointmentId/{appointmentId}")
	@Operation(summary = "Filter booking details by appointment_ID", description = "To get booking details for particular appointment by appointment_ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202",description = "appointments details will be fetched by appointments_ID"),
			@ApiResponse(responseCode = "400",description = "Invalid appointment_ID found..")
	})
	public ResponseEntity<BookAppointmentDto> getBookingDetailsByAppointmentID(@PathVariable("appointmentId") long appointmentId)
	{
		return new ResponseEntity<BookAppointmentDto>(bookService.getBookingDetailsByAppointmentID(appointmentId),HttpStatus.OK);
	}
	
	@GetMapping("/booking-details/emailId/{customerEmailId}")
	@Operation(summary = "Filter book appointment details by registered customer email ID", description = "To get book appointment details by customer registered email Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202",description = "Booking details will be fetched by booked customer email ID"),
			@ApiResponse(responseCode = "400",description = "You haven't booked any appointment from your account..")
	})
	public ResponseEntity<List<BookAppointmentDto>> getBookingDetailsByCustomerEmailId(@PathVariable("customerEmailId") String customerEmailId)
	{
		return new ResponseEntity<>(bookService.getBookingDetailsByCustomerEmailId(customerEmailId),HttpStatus.OK);
	}
	
	@GetMapping("/{customerEmailId}/{appointmentDate}")
	@Operation(summary = "Filter by appointment Date and customer email ID",description = "To get all appointment by filtering appointmentDate and customer email_Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202",description = "Will return list of appointments by filtering appointment date and customer email_ID"),
			@ApiResponse(responseCode = "400", description = "Oops..No appointment book for the given input..")
	})
	public ResponseEntity<List<BookAppointmentDto>> getBookingDetailsByCustomerEmailIdAndBookingDate(@PathVariable("customerEmailId") String customerEmailId,@PathVariable("appointmentDate") String appointmentDate )
	{
		LocalDate date = LocalDate.parse(appointmentDate);
		return new ResponseEntity<>(bookService.getBookingDetailsByCustomerEmailIdAndBookingDate(customerEmailId,date),HttpStatus.OK);
	}
	@PutMapping("/{appointmentId}")
	@Operation(summary = "update booked appointment ",description = "To update booked appointment details by particular appointment_Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "will return update appointment by particular appointment_Id"),
			@ApiResponse(responseCode = "400",description = "You haven't update appointment because invalid appointment_Id")
	})
	public ResponseEntity<BookAppointmentDto> updateBookingDetail(@PathVariable("appointmentId") long appointmentId,@RequestBody BookAppointmentDto bookAppointmentDto)
	{
		return new ResponseEntity<BookAppointmentDto>(bookService.updateBookingDetail(appointmentId,bookAppointmentDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{appointmentId}")
	@Operation(summary = "cancel booked appointment",description = "you can cancel booked appointment by appointment_Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description ="will return true if appointment get cancelled successfully otherwise it will return false" )
	})
	public ResponseEntity<BookAppointmentDto> bookingCancellation(@PathVariable("appointmentId") long appointmentId)
	{
		return new ResponseEntity<BookAppointmentDto>(bookService.cancelBookingDetails(appointmentId),HttpStatus.OK);
	}
}
