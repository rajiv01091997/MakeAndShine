package com.stackroute.packageservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.packageservice.model.Package;
import com.stackroute.packageservice.repository.PackageRepository;
import com.stackroute.packageservice.Exception.IdNotFoundException;
import com.stackroute.packageservice.dto.PackageDetailsDto;
import com.stackroute.packageservice.dto.PackageDto;
@Service
public class PackageServiceImpl implements PackageServiceInterface{
@Autowired
private PackageRepository packageRepository;

@Autowired
private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public PackageDto addPackageService(PackageDto packageDto) {
		Package package1=convertToEntity(packageDto);
		List<com.stackroute.packageservice.model.Service> services=packageDto.getServices();
		double amount=0;
		for (com.stackroute.packageservice.model.Service service : services) {
			amount+=service.getAmount();
		}
		package1.setPackageId(sequenceGeneratorService.generateSequence(Package.SEQUENCE_NAME));
		package1.setPackageAmount(amount);
		packageDto.setPackageAmount(amount);
		packageRepository.save(package1);
		packageDto.setPackageId(package1.getPackageId());
		return packageDto;
	}
    @Override
	public List<PackageDto> getAllPackages() {
		List<Package> packages= packageRepository.findAll();
		List<PackageDto>packageDtos=new ArrayList<>();
		for (Package package1 : packages) {
			PackageDto packageDto=convertToDto(package1);
			packageDtos.add(packageDto);
			}
		return packageDtos;
	}

	@Override
	public PackageDto getPackageDetailsByPackageId(long packageId) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}
		PackageDto packageDto=convertToDto(package1.get());
		return packageDto;
	}

	@Override
	public PackageDto updatePackageServiceById(long packageId, PackageDto packageDto) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}	
		List<com.stackroute.packageservice.model.Service> services=packageDto.getServices();
		double amount=0;
		for (com.stackroute.packageservice.model.Service service : services) {
			System.out.println(service.getAmount());
			amount+=service.getAmount();
		}
		package1.get().setPackageDescription(packageDto.getPackageDescription());
		package1.get().setPackageImage(packageDto.getPackageImage());
		package1.get().setPackageName(packageDto.getPackageName());
		package1.get().setServices(packageDto.getServices());
		System.out.println(amount);
		package1.get().setPackageAmount(amount);
		packageRepository.save(package1.get());
		packageDto.setPackageAmount(amount);
		return packageDto ;
	}
	

	@Override
	public void deletePackageId(long packageId) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
			}
		packageRepository.delete(package1.get());
	}
	@Override
	public void addPackageImage(long packageId, byte[] image) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}
		package1.get().setPackageImage(image);
		packageRepository.save(package1.get());
		
	}
	@Override
	public com.stackroute.packageservice.model.Service addNewService(long packageId,
			com.stackroute.packageservice.model.Service service) {
		
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}
		List<com.stackroute.packageservice.model.Service> services = package1.get().getServices();
		for(com.stackroute.packageservice.model.Service service1 : services)
		{
			if(service1.getServiceId()==service.getServiceId())
			{
				throw new ResourceNotFoundException("Id is already present");
			}
		}
		services.add(service);
		package1.get().setServices(services);
		double amount = package1.get().getPackageAmount();
		package1.get().setPackageAmount(amount+service.getAmount());
		packageRepository.save(package1.get());
		return service;
	}
	@Override
	public PackageDetailsDto updatePackageDetails(long packageId, PackageDetailsDto packageDetailsDto) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}
		package1.get().setPackageName(packageDetailsDto.getPackageName());
		package1.get().setPackageDescription(packageDetailsDto.getPackageDescription());
		packageRepository.save(package1.get());
		return packageDetailsDto;
	}
	@Override
	public com.stackroute.packageservice.model.Service deleteAService(long packageId, long serviceId) {
		Optional<Package> package1=packageRepository.findById(packageId);
		if (package1.isEmpty()) {
			throw new IdNotFoundException("no package found");
		}

		double amount = package1.get().getPackageAmount();
		for(com.stackroute.packageservice.model.Service service : package1.get().getServices())
		{
			if(service.getServiceId()==serviceId)
			{
				amount-=service.getAmount();
				package1.get().getServices().remove(service);
				package1.get().setPackageAmount(amount);
				packageRepository.save(package1.get());
				return service;
			}
		}

		throw new ResourceNotFoundException("No Service Id is present");
	}
    @Override
	public Package convertToEntity(PackageDto packageDto) {
	    Package package1=new Package();
		package1=new ModelMapper().map(packageDto,Package.class);
		return package1;
	}
    @Override
	public PackageDto convertToDto(Package package1) {
		PackageDto packageDto=new PackageDto();
		packageDto=new ModelMapper().map(package1, PackageDto.class);
				return packageDto;
	}
	
	
	
	
    
	
}

