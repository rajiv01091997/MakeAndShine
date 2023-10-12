package com.stackroute.appointmentservice.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.stackroute.appointmentservice.dto.EmployeeAppointmentDto;
import com.stackroute.appointmentservice.entity.EmployeeAppointment;
import com.stackroute.appointmentservice.exception.ResourceNotFoundException;
import com.stackroute.appointmentservice.interfaces.EmployeeAppointmentService;
import com.stackroute.appointmentservice.repository.EmployeeAppointmentRepository;


@Service
public  class EmployeeAppointmentServiceImpl implements EmployeeAppointmentService{

	@Autowired
	private EmployeeAppointmentRepository employeeRepository;


	/*
	 * @Description : This method is used to create a new appointment and stores the data into database
	 * @Params : This method contains one parameter of type EmployeeAppointmentDto
	 * @Author : jyoti
	 */
	@Override
	public EmployeeAppointmentDto addAppointmentDto(EmployeeAppointmentDto employeeAppointmentDto) {
		System.out.println(employeeAppointmentDto);
          EmployeeAppointment  employeeAppointment = convertToEntity(employeeAppointmentDto);
		
          System.out.println(employeeAppointment);
		return convertToDto(employeeRepository.save(employeeAppointment));
	}

	@Override
	public List<EmployeeAppointmentDto> getAppointmentDetailByServiceId(long serviceId) {
	List<EmployeeAppointment> list = employeeRepository.findByServiceId(serviceId);
	List<EmployeeAppointmentDto> listDto = new ArrayList<>();
	for (EmployeeAppointment employeeAppointment : list) {
		
		EmployeeAppointmentDto employeeAppointmentDto = convertToDto(employeeAppointment);
		listDto.add(employeeAppointmentDto);
	}
		return listDto;
	}
	/*
	 * @Description : This method is to get list of appointment is available for particular employee from database by employee Email_ID
	 * @Params : This method contains one parameter of type string employee Email_Id
	 * @Author : jyoti
	 */
	@Override
	public List<EmployeeAppointmentDto> getAppointmentDetailByEmployeeEmailId(String employeeEmailId) {
		List<EmployeeAppointment> list = employeeRepository.findByEmployeeEmailId(employeeEmailId);
		List<EmployeeAppointmentDto> listDto = new ArrayList<>();
		for (EmployeeAppointment employeeAppointment : list) {
			
			EmployeeAppointmentDto employeeAppointmentDto = convertToDto(employeeAppointment);
			listDto.add(employeeAppointmentDto);
		}
			return listDto;
	}

	/*
	 * @Description : This method is used to get the particular appointment details from database by Employee Email_ID and AppointmentDate
	 * @Params : This method contains one parameter of type string Employee Email_ID and AppointmentDate
	 * @Author : jyoti
	 */
	@Override
	public List<EmployeeAppointmentDto> getAppointmentDetailByEmployeeEmailIdAndAppointmentDate(String employeeEmailId,
			LocalDate appointmentDate) {
		List<EmployeeAppointment> list = employeeRepository.findByEmployeeEmailIdAndAppointmentDate(employeeEmailId,appointmentDate);
		List<EmployeeAppointmentDto> listDto = new ArrayList<>();
		for (EmployeeAppointment employeeAppointment : list) {
			
			EmployeeAppointmentDto employeeAppointmentDto = convertToDto(employeeAppointment);
			listDto.add(employeeAppointmentDto);
		}
			return listDto;
	}
	/*
	 * @Description : This method is used to get the list of  appointment is available for booking
	 * @Author : jyoti
	 */
	@Override
	public List<EmployeeAppointmentDto> getAllAppointmentDetails() {
		List<EmployeeAppointment> list = employeeRepository.findAll();
		List<EmployeeAppointmentDto> listDto = new ArrayList<>();
		for (EmployeeAppointment employeeAppointment : list) {
			
			EmployeeAppointmentDto employeeAppointmentDto = convertToDto(employeeAppointment);
			listDto.add(employeeAppointmentDto);
		}
			return listDto;	}

	/*
	 * @Description : This method is used to get the particular booked list of appointment details from database by serviceId and Appointment_Date
	 * @Params : This method contains two parameter of type long service_Id and localDate appointment_Date
	 * @Author : jyoti
	 */
	@Override
	public List<EmployeeAppointmentDto> getAppointmentDetailByServiceIdAndAppointmentDate(long serviceId,
			LocalDate appointmentDate) {
		List<EmployeeAppointment> list = employeeRepository.findByServiceIdAndAppointmentDate(serviceId,appointmentDate);
		List<EmployeeAppointmentDto> listDto = new ArrayList<>();
		for (EmployeeAppointment employeeAppointment : list) {
			
			EmployeeAppointmentDto employeeAppointmentDto = convertToDto(employeeAppointment);
			listDto.add(employeeAppointmentDto);
		}
			return listDto;
	}


	@Override
	public EmployeeAppointmentDto updateAppointmentDetails(long appointmentId, EmployeeAppointmentDto employeeAppointmentDto) 
	{
		Optional<EmployeeAppointment> employeeAppointOptional = employeeRepository.findById(appointmentId);
		if(employeeAppointOptional.isEmpty())
			throw new ResourceNotFoundException("No Id is present :"+appointmentId); 
		EmployeeAppointment employeeAppointment = new EmployeeAppointment();
		employeeAppointment.setAppointmentId(employeeAppointOptional.get().getAppointmentId());
		employeeAppointment.setAppointmentDate(employeeAppointment.getAppointmentDate());
		employeeAppointment.setAppointmentStartTime(employeeAppointment.getAppointmentStartTime());
		employeeAppointment.setAppointmentEndTime(employeeAppointment.getAppointmentEndTime());
		employeeAppointment.setEmployeeEmailId(employeeAppointment.getEmployeeEmailId());
		employeeAppointment.setServiceId(employeeAppointment.getServiceId());
	  
		employeeRepository.save(employeeAppointment);
		return employeeAppointmentDto;
	}

	@Override
	public void cancelAppointmentDetails(long appointmentId) {
		EmployeeAppointment employeeAppointment = employeeRepository.findById(appointmentId).get();
		employeeAppointment.setIsAvailable(true);
		employeeRepository.save(employeeAppointment); 
	}

	/*
	 * @Description : This method is used to convert EmployeeAppointmentDto to EmployeeAppointment by using ModelMapper class
	 * @Params : This method contains one parameter of type EmployeeAppointmentDto.
	 * @Author : jyoti
	 */
	public EmployeeAppointment convertToEntity(EmployeeAppointmentDto employeeAppointmentDto)
	{
		EmployeeAppointment employeeAppointment = new EmployeeAppointment();
		employeeAppointment = new ModelMapper().map(employeeAppointmentDto, EmployeeAppointment.class);
		return employeeAppointment;
	}
	/*
	 * @Description : This method is used to convert EmployeeAppointment to EmployeeAppointmentDto by using ModelMapper class
	 * @Params : This method contains one parameter of type EmployeeAppointment
	 * @Author : jyoti
	 */
	public EmployeeAppointmentDto convertToDto(EmployeeAppointment employeeAppointment)
	{
		EmployeeAppointmentDto employeeAppointmentDto = new EmployeeAppointmentDto();
		employeeAppointmentDto = new ModelMapper().map(employeeAppointment, EmployeeAppointmentDto.class);
		return employeeAppointmentDto;
	} 

      	
      	
		
}
