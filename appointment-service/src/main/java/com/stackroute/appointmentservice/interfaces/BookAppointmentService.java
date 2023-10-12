package com.stackroute.appointmentservice.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.appointmentservice.dto.BookAppointmentDto;
import com.stackroute.appointmentservice.entity.BookAppointment;

@Service
public interface BookAppointmentService {
 
	BookAppointmentDto addBookingAppointment(BookAppointmentDto bookAppointmentDto);     //To store details for booked appointments

	BookAppointmentDto getBookingDetailsByAppointmentID(long appointmentId);           //To get booked appointment details by appointment_Id

	List<BookAppointmentDto> getBookingDetailsByCustomerEmailId(String customerEmailId);      //To get booked appointment details by customer email_Id

	List<BookAppointmentDto> getBookingDetailsByCustomerEmailIdAndBookingDate(String customerEmailId, LocalDate appointmentDate);    //To get all booked appointment by filtering appointment for particular appointment by using customer email_Id and appointment_Id

	BookAppointmentDto updateBookingDetail(long appointmentId, BookAppointmentDto bookAppointmentDto);         //To update the booked appointment by using appointment_Id

	BookAppointmentDto cancelBookingDetails(long appointmentId);       //Method is used for cancel booked appointment by using appointment_Id

	BookAppointmentDto convertToDto(BookAppointment fakeObj1);        //Here we convert entity class fake object into Dto class for test cases


	
	
	
	
	
}
