package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.constants.AppConstants;
import com.stackroute.dto.SalonServiceDto;
import com.stackroute.entity.SequenceGeneratorService;
import com.stackroute.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class SalonController {

	@Autowired
	private SalonService salonService;
	
	@Autowired
	public SequenceGeneratorService sequenceGeneratorService;


	/**
	 * To Get All The SalonService From DataBase
	 **/
	@GetMapping("/salon")
	public ResponseEntity<List<SalonServiceDto>> getAllSalons() {
		return new ResponseEntity<>(salonService.getAllSalons(), HttpStatus.OK);
	}

	/**
	 * To Save The SalonSerivce to DataBase
	 **/
	@PostMapping("/salon")
	public ResponseEntity <SalonServiceDto>addSalon( @Validated @RequestBody SalonServiceDto salonDto) {


		return new ResponseEntity<SalonServiceDto>(salonService.addSalon(salonDto), HttpStatus.CREATED);
	}
	@PostMapping("/addImage")
	public ResponseEntity addSalon(@RequestParam("data") String salonDto,
								   @RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
		//@RequestParam(value = "file",required = false) MultipartFile file,
//		@RequestParam(value = "file",required = false) MultipartFile file
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		SalonServiceDto salonDto1= objectMapper.readValue(salonDto,SalonServiceDto.class);
		salonDto1.setServiceImage(image);
		return new ResponseEntity<>(salonService.addSalon(salonDto1), HttpStatus.OK);
}
	/**
	 * To Get the SalonService from database
	 **/
	@GetMapping("/{serviceid}")
	public ResponseEntity<SalonServiceDto> getSalonByID(@PathVariable Long serviceid) {

		return new ResponseEntity<>(salonService.getSalonById(serviceid), HttpStatus.OK);

	}

	/**
	 * To update the service
	 **/
	@PutMapping("/{serviceid}")
	public ResponseEntity<SalonServiceDto> updateSalonById(@PathVariable Long serviceid, @RequestBody SalonServiceDto salonDto) {

		return new ResponseEntity<>(salonService.updateSalonById(serviceid, salonDto), HttpStatus.OK);

	}
   @DeleteMapping("/{serviceid}")
	public ResponseEntity<String> deleteSalonById(@PathVariable Long serviceid) {
		salonService.deleteSalonById(serviceid);
		return new ResponseEntity<>(AppConstants.DELETE_MESSAGE, HttpStatus.OK);
	}
}
