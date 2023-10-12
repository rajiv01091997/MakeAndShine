package com.stackroute.appointmentservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.appointmentservice.entity.BookAppointment;


@Repository
public interface BookAppointmentRepository  extends MongoRepository<BookAppointment, Long>{

	List<BookAppointment> findByCustomerEmailId(String customerEmailId);     //To get booked appointment records by using customer email_id

	List<BookAppointment> findByCustomerEmailIdAndBookSlotDate(String customerEmailId, LocalDate appointmentDate);     //To get booked appointment records by using customer email_id and appointment and booking date

	List<BookAppointment> findByEmployeeEmailIdAndBookSlotDate(String employeeEmailId, LocalDate appointmentDate);    //To get booked appointment details by the help of employee emailId and booking date



}
