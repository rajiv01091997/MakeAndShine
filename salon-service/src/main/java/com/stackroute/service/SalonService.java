package com.stackroute.service;

import java.util.List;

import com.stackroute.dto.SalonServiceDto;
import com.stackroute.entity.Salon;

public interface SalonService {

	List<SalonServiceDto> getAllSalons();

	SalonServiceDto addSalon(SalonServiceDto salonDto);

	SalonServiceDto getSalonById(Long serviceid);

	SalonServiceDto updateSalonById(Long serviceid, SalonServiceDto salonDto);

	 SalonServiceDto deleteSalonById(Long serviceid);

	SalonServiceDto convertToDto(Salon salon);

	Salon convertToEntity(SalonServiceDto salonDto);

}
