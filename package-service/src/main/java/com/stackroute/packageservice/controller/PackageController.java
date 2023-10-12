package com.stackroute.packageservice.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.packageservice.dto.PackageDetailsDto;
import com.stackroute.packageservice.dto.PackageDto;
import com.stackroute.packageservice.model.Service;
import com.stackroute.packageservice.service.PackageServiceInterface;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/v5/package")
public class PackageController {

	@Autowired
	private PackageServiceInterface packageService;
	
	@PostMapping("/")
	public ResponseEntity addPackageService(@RequestPart(value = "file",required = false) MultipartFile file , @RequestPart(value = "data") String packageDto) throws IOException
	{
		byte[] image=null;
		try {
			image = file.getBytes();
		}
		catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		PackageDto packageDto2 = objectMapper.readValue(packageDto,PackageDto.class);
		packageDto2.setPackageImage(image);

		return new ResponseEntity<PackageDto>(packageService.addPackageService(packageDto2),HttpStatus.OK);
	}
	
     @GetMapping("/")
     public ResponseEntity<List<PackageDto>> getAllPackages(){
    	 
    	 return new ResponseEntity<List<PackageDto>>(packageService.getAllPackages(),HttpStatus.OK);
     }
     @GetMapping("/{packageId}")
     public ResponseEntity<PackageDto> getPackageDetailsById(@PathVariable long packageId){
    	 return new ResponseEntity<PackageDto>(packageService.getPackageDetailsByPackageId(packageId),HttpStatus.OK);
    	  }
     
     
     

     @DeleteMapping("{packageId}")
     public void deletePackage(@PathVariable long packageId) {
    	 packageService.deletePackageId(packageId);
     }
     
     @PatchMapping("/image/{packageId}")
     public ResponseEntity<String> updatePackageImage(@PathVariable long packageId, @RequestPart(value = "file",required = false) MultipartFile file)
     {
    	 byte[] image=null;
 		try {
 			image = file.getBytes();
 		}
 		catch (Exception e){
 			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
 		}
 		packageService.addPackageImage(packageId,image);
 		return new ResponseEntity<String>("Uploaded sucessfully",HttpStatus.OK);
     }
     
     @PatchMapping("/service/{packageId}")
     public ResponseEntity<Service> addNewService(@PathVariable long packageId,@RequestBody Service service)
     {
    	 return new ResponseEntity<Service>(packageService.addNewService(packageId,service),HttpStatus.OK);
     }
     
  @PatchMapping("/service/{packageId}/{serviceId}")
     public ResponseEntity<Service> deleteAService(@PathVariable long packageId,@PathVariable long serviceId)
    {
   	 return new ResponseEntity<Service>(packageService.deleteAService(packageId,serviceId),HttpStatus.OK);
   }
     
     @PatchMapping("/details/{packageId}")
     public ResponseEntity<PackageDetailsDto> updatePackageDetails(@PathVariable long packageId,@RequestBody PackageDetailsDto packageDetailsDto )
     {
    	 return new ResponseEntity<PackageDetailsDto>(packageService.updatePackageDetails(packageId,packageDetailsDto),HttpStatus.OK);
     }
     
     
}

