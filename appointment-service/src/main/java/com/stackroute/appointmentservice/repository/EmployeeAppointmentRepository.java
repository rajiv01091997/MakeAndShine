package com.stackroute.appointmentservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.appointmentservice.entity.EmployeeAppointment;


@Repository
public interface EmployeeAppointmentRepository extends MongoRepository<EmployeeAppointment, Long> {

	List<EmployeeAppointment> findByServiceId(long serviceId);     //To check availability of appointment list by using serviceId

	List<EmployeeAppointment> findByEmployeeEmailId(String employeeEmailId);     //To check availability of appointment list by using employee email_Id

	List<EmployeeAppointment> findByEmployeeEmailIdAndAppointmentDate(String employeeEmailId, LocalDate appointmentDate);     //To get appointment list by using employee emailId and appointment Date

	List<EmployeeAppointment> findByServiceIdAndAppointmentDate(long serviceId, LocalDate appointmentDate);          //To get appointment list by using  service_Id and appointment Date



	


}
