package com.stackroute.packageservice.service;

import java.util.List;

import com.stackroute.packageservice.dto.PackageDetailsDto;
import com.stackroute.packageservice.dto.PackageDto;
import com.stackroute.packageservice.model.Package;
import com.stackroute.packageservice.model.Service;

public interface PackageServiceInterface {

	PackageDto addPackageService(PackageDto  packageDto);

	PackageDto getPackageDetailsByPackageId(long packageId);
    List<PackageDto> getAllPackages();
	PackageDto updatePackageServiceById(long packageId, PackageDto packageDto);
     void deletePackageId(long packageId);
     Package convertToEntity(PackageDto packageDto);
     PackageDto convertToDto(Package package1);

	void addPackageImage(long packageId, byte[] image);

	Service addNewService(long packageId,Service service);

	PackageDetailsDto updatePackageDetails(long packageId, PackageDetailsDto packageDetailsDto);

	Service deleteAService(long packageId, long service);
}