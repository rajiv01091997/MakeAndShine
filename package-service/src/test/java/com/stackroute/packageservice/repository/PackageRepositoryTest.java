package com.stackroute.packageservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.packageservice.model.Service;
import com.stackroute.packageservice.model.Package;
@SpringBootTest 
public class PackageRepositoryTest {
	@Autowired
	@MockBean
	private PackageRepository packageRepository;
//	@Test
//	void getAllPackageTest()
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1=new Package(101,"facial","Bridal",services,5000,null);
//		Package package2=new Package(102,"hair","hairStyle",services,9000,null);
//
//	}
//	@Test
//	void createPackageTest(){
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1=new Package(103,"makeup","groom",services,6000,null);
//
//		when(packageRepository.save(package1)).thenReturn(package1);
//		Package package2 =packageRepository.save(package1);
//		assertEquals(package2.getPackageId(),package1.getPackageId());
//
//	}
//
//    @Test
//    void getPackageDetailsByPackageId() {
//    	Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		Package package1=new Package(103,"makeup","groom",services,6000,null);
//		long PackageId=101;
//		when(packageRepository.findById(package1.getPackageId())).thenReturn(Optional.of(package1));
//		Package package3=packageRepository.findById(package1.getPackageId()).get();
//		assertEquals(package3.getPackageId(),package1.getPackageId());
//
//
//		}
//		@Test
//	void updatePackageServiceById(){
//			Service service=new Service(1,"hair",3000);
//			Service service1=new Service(2,"nail",4000);
//			Service service2=new Service(3,"makeup",5000);
//			List<Service>services=new ArrayList<>();
//			services.add(service);
//			services.add(service1);
//			services.add(service2);
//			Package package1=new Package(103,"makeup","groom",services,6000,null);
//
//		}
}
