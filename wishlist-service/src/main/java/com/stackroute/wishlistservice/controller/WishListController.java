package com.stackroute.wishlistservice.controller;

import com.stackroute.wishlistservice.entity.Package;
import com.stackroute.wishlistservice.entity.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.wishlistservice.dto.WishListDto;
import com.stackroute.wishlistservice.service.IWishlistService;

@RestController
@RequestMapping("/api/v1")
public class WishListController {
	@Autowired
	private IWishlistService wishlistservice;
	
	
	@PostMapping("/wishlist/{emailid}")
	public ResponseEntity<WishListDto> createWishById(@PathVariable(value = "emailid") String emailid) {

		return new ResponseEntity<WishListDto>(wishlistservice.createWishList(emailid), HttpStatus.OK);
	}
	
	
	

	@PostMapping("/wishlist/service/{emailid}")
	public ResponseEntity<WishListDto> addService(@PathVariable(value = "emailid") String emailid,  @RequestParam String services,@RequestPart(value = "file",required = false) MultipartFile file )throws IOException {
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity<WishListDto>(HttpStatus.CONFLICT);
		}
	    ObjectMapper objectMapper = new ObjectMapper();
	    Services service1 = objectMapper.readValue(services,Services.class);
		
		service1.setServicePhoto(image);

				
		return new ResponseEntity<>(wishlistservice.addServcie(emailid, service1), HttpStatus.CREATED);

	}
	
	@PostMapping("/wishlist/package/{emailid}")
	public ResponseEntity<WishListDto> addPackage(@PathVariable(value = "emailid") String emailid,   @RequestParam String packages ,@RequestPart(value = "file",required = false) MultipartFile file ) throws IOException {

		
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity<WishListDto>(HttpStatus.CONFLICT);
		}
	    ObjectMapper objectMapper = new ObjectMapper();
	    Package package1 = objectMapper.readValue(packages,Package.class);
		
		package1.setPackagephoto(image);


		return new ResponseEntity<WishListDto>(wishlistservice.addPackage(emailid,package1) ,HttpStatus.CREATED);

	}
	

	@PutMapping("/wishlist/package/{emailid}/{packageId}")
	public ResponseEntity<String> deletePackage(@PathVariable(value = "emailid") String emailid,@PathVariable(value = "packageId") long packageId) {
		
		wishlistservice.deletePackage(emailid, packageId);
	
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}
	
	
	@PutMapping("/wishlist/service/{emailid}/{serviceId}")
	public ResponseEntity<String> deleteService(@PathVariable(value = "emailid") String emailid  , @PathVariable(value = "serviceId") long serviceId) {
      wishlistservice.deleteService(emailid,serviceId);
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
	@GetMapping("/wishlist/{emailid}")
	public ResponseEntity<WishListDto> getwishlist(@PathVariable(value = "emailid") String emailid) {

		return new ResponseEntity<WishListDto>(wishlistservice.getWishListbyId(emailid), HttpStatus.OK);
	}
	
	
}
