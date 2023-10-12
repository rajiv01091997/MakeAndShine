package com.stackroute.packageservice.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.packageservice.Exception.IdNotFoundException;
import com.stackroute.packageservice.dto.PackageDto;
import com.stackroute.packageservice.model.Package;
import com.stackroute.packageservice.model.Service;
import com.stackroute.packageservice.repository.PackageRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PackageServiceTest {

	@MockBean
	private PackageRepository packageRepository;

	@Autowired
	private PackageServiceInterface packageServiceInterface;

//	@Test
//	void getAllUsersTest()
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1 = new Package(101, "Bridal", "for marriages", services, 20000, null);
//		Package package2 = new Package(102, "Function", "for function", services, 30000, null);
//
//		List<Package> packageDtos = new ArrayList<>();
//		packageDtos.add(package1);
//		packageDtos.add(package2);
//
//		when(packageRepository.findAll()).thenReturn(packageDtos);
//
//		List<PackageDto> packageDtos2 = packageServiceInterface.getAllPackages();
//
//		assertEquals(packageDtos.size(), packageDtos2.size());
//
//	}
//
//	@Test
//	void getPackageDetailsById()
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1 = new Package(101, "Bridal", "for marriages", services, 20000, null);
//		long id = 101;
//		when(packageRepository.findById(id)).thenReturn(Optional.of(package1));
//
//		PackageDto packageDto = packageServiceInterface.getPackageDetailsByPackageId(id);
//
//		assertEquals(packageDto.getPackageId(), package1.getPackageId());
//		assertEquals(package1.getPackageName(), package1.getPackageName());
//		assertEquals(packageDto.getPackageAmount(), package1.getPackageAmount());
//	}
//
//	@Test
//	void getExceptionByWrongId()
//	{
//		long id =101;
//		when(packageRepository.findById(id)).thenReturn(Optional.empty());
//		Optional<Package> package1 = packageRepository.findById(id);
//		when(packageRepository.findById(id)).thenReturn(package1);
//		assertTrue(packageRepository.findById(id).isEmpty());
//		assertThrows(IdNotFoundException.class, ()->{
//			packageServiceInterface.getPackageDetailsByPackageId(id);
//		});
//	}
//
//	@Test
//	void createNewUserTest()
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1 = new Package(101, "Bridal", "for marriages", services, 20000, null);
//		when(packageRepository.save(package1)).thenReturn(package1);
//
//		PackageDto packageDto = packageServiceInterface.convertToDto(package1);
//		PackageDto packageDto2 = packageServiceInterface.addPackageService(packageDto);
//
//		assertEquals(packageDto2.getPackageId(),package1.getPackageId());
//		assertEquals(packageDto2.getPackageName(), package1.getPackageName());
//	}
//
//	@Test
//	void updateUserTest()
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1 = new Package(101, "Bridal", "for marriages", services, 20000, null);
//		long id = 101;
//		when(packageRepository.findById(id)).thenReturn(Optional.of(package1));
//		PackageDto packageDto = packageServiceInterface.convertToDto(package1);
//		packageDto.setPackageDescription("for All");
//		when(packageServiceInterface.updatePackageServiceById(id,packageDto)).thenReturn(packageDto);
//		PackageDto packageDto2 = packageServiceInterface.updatePackageServiceById(id,packageDto);
//		assertEquals(packageDto2.getPackageDescription(),package1.getPackageDescription());
//
//	}
//
//	@Test
//	void updateUserWhenNoUserIsThere()
//	{
//		long id =101;
//		when(packageRepository.findById(id)).thenReturn(Optional.empty());
//
//		assertThrows(IdNotFoundException.class, ()->{
//			packageServiceInterface.updatePackageServiceById(id, any(PackageDto.class));
//		});
//	}
}
