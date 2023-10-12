package com.stackroute.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.stackroute.entity.SequenceGeneratorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.constants.AppConstants;
import com.stackroute.dto.SalonServiceDto;
import com.stackroute.entity.Salon;
import com.stackroute.exception.InvalidPriceException;
import com.stackroute.exception.ResourceNotFoundException;
import com.stackroute.repository.SalonRepository;
import com.stackroute.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {
	@Autowired
	SalonRepository salonRepository;


	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	/**
	 * @description : Fetches All Persons from Database
	 * @return List<SalonServiceDto> : List of all services
	 * 
	 */
	@Override
	public List<SalonServiceDto> getAllSalons() {

		return salonRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * @description : Saves the Person to Database
	 * @param salonDto : Person to be saved into database
	 * @return salonDto : Saved Salon
	 * 
	 */
	@Override
	public SalonServiceDto addSalon(SalonServiceDto salonDto) {
		double cost = salonDto.getCost();
		if (cost <= 0) {

			throw new InvalidPriceException(
					"Invalid Price : " + cost + " (Price cannot be less than or equal to zero)");
		}
		Salon salon = salonRepository.save(convertToEntity(salonDto));
		SalonServiceDto salonServiceDto = convertToDto(salon);



		salonServiceDto.setServiceid(sequenceGeneratorService.getSequenceNumber(Salon.SEQUENCE_NAME));


		return salonServiceDto;
	}

	/**
	 * @description : Fetches the Salon by using his ID
	 * @param serviceid : ID of the Salon to be fetched
	 * @return salonDto : salon matching the passed ID
	 * @throws ResourceNotFoundException : If Person with given ID not found
	 */
	@Override
	public SalonServiceDto getSalonById(Long serviceid) {
		Salon salon = salonRepository.findById(serviceid)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.RESOURCE_NOT_FOUND_MESSAGE +serviceid));
		return convertToDto(salon);
	}

	@Override
	public SalonServiceDto updateSalonById(Long serviceid, SalonServiceDto salonDto) {
		Salon salon = salonRepository.findById(serviceid)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.RESOURCE_NOT_FOUND_MESSAGE +serviceid));
		salon.setServiceid(salonDto.getServiceid());
		salon.setServiceCategory(salonDto.getServiceCategory());
		salon.setServiceName(salonDto.getServiceName());
		salon.setServiceDescription(salonDto.getServiceDescription());
		salon.setCost(salonDto.getCost());
		salon.setGender(salonDto.getGender());


		salonRepository.save(salon);
		return convertToDto(salonRepository.save(salon));
	}

	/**
	 * @param serviceid : ID of the Salon to be Deleted
	 * @return
	 * @throws ResourceNotFoundException : If Salon with given ID not found
	 * @description : Deletes the salon by using his ID
	 */
	@Override
	public SalonServiceDto deleteSalonById(Long serviceid) {
		if(salonRepository.findById(serviceid).isEmpty())
			throw new ResourceNotFoundException("No ID found to delete : "+serviceid);
		salonRepository.delete(salonRepository.findById(serviceid).get());

		return null;
	}
	

	/**
	 * Parameters:
	 * 
	 * @salon : Salon Entity to be converted into Person DTO
	 * @Returns : salonDto : Salon DTO
	 * @description : Helper Method which converts Entity to DTO
	 */
	public SalonServiceDto convertToDto(Salon salon) {
 	SalonServiceDto salonDto = new SalonServiceDto();
		 salonDto = new ModelMapper().map(salon, SalonServiceDto.class);
		return salonDto;
	}

	/**
	 * @description : Helper Method which converts DTO to Entity
	 * @param salonDto : Salon DTO to be converted into Salon Entity
	 * @return salon : Salon Entity
	 */
	public Salon convertToEntity(SalonServiceDto salonDto) {
		Salon salon = new ModelMapper().map(salonDto, Salon.class);
		salon.setTime(LocalTime.parse(salonDto.getTime()));
		return salon;
	}
}
