package com.stackroute.packageservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.packageservice.Exception.IdNotFoundException;
import com.stackroute.packageservice.dto.PackageDto;
import com.stackroute.packageservice.model.Service;
import com.stackroute.packageservice.repository.PackageRepository;
import com.stackroute.packageservice.service.PackageServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PackageController.class)
@ActiveProfiles("test")
public class PackageControllerTest {

	@MockBean
	private PackageRepository packageRepository;

	@MockBean
	private PackageServiceImpl packageService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper = new ObjectMapper();

//	@Test
//	void testGetAllMethods() throws Exception
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		PackageDto packageDto = new PackageDto(101, "Bridal", "for marriages", services, 20000, null);
//		PackageDto packageDto1 = new PackageDto(102, "Function", "for function", services, 30000, null);
//
//		List<PackageDto> packageDtos = new ArrayList<>();
//		packageDtos.add(packageDto1);
//		packageDtos.add(packageDto);
//
//		when(packageService.getAllPackages()).thenReturn(packageDtos);
//
//		this.mockMvc.perform(get("/api/v2/package/"))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.size()", is(packageDtos.size())));
//	}
//
//	@Test
//	void createPackageMethod() throws Exception
//	{
//		String data="{{\n"
//				+ "    \"packageId\":101,\n"
//				+ "    \"packageName\":\"Bridal\",\n"
//				+ "    \"packageDescription\":\"will done all\",\n"
//				+ "    \"services\":[\n"
//				+ "        {\n"
//				+ "            \"serviceId\":1,\n"
//				+ "            \"serviceName\":\"hair\",\n"
//				+ "            \"amount\":2000\n"
//				+ "        },\n"
//				+ "        {\n"
//				+ "            \"serviceId\":2,\n"
//				+ "            \"serviceName\":\"face\",\n"
//				+ "            \"amount\":80000\n"
//				+ "        }\n"
//				+ "\n"
//				+ "    ]\n"
//				+ "}}";
//
//		PackageDto packageDto2 = objectMapper.readValue(data,PackageDto.class);
//
//		when(packageService.addPackageService(any(PackageDto.class))).thenAnswer((invocation) -> invocation.getArgument(0));
//		mockMvc.perform(post("/api/v2/package/")
//        .contentType("application/json")
//        .content(objectMapper.writeValueAsString(packageDto2)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.packageId", is(packageDto2.getPackageId())))
//        .andExpect(jsonPath("$.packageName", is(packageDto2.getPackageName())));
//        //.andExpect(jsonPath("$.age", is(userDto.getAge())));
//
//	}
//
//	@Test
//	void getPackageById() throws Exception
//	{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		PackageDto packageDto = new PackageDto(101, "Bridal", "for marriages", services, 20000, null);
//
//		when(packageService.getPackageDetailsByPackageId(packageDto.getPackageId())).thenReturn(packageDto);
//
//		this.mockMvc.perform(get("/api/v2/package/{packageId}",packageDto.getPackageId()))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.packageDescription", is(packageDto.getPackageDescription())))
//	        .andExpect(jsonPath("$.packageName", is(packageDto.getPackageName())));
//	}
//
//	@Test
//	void getExpectionWhilePassingWrongId() throws Exception
//	{
//		long id = 101;
//		when(packageService.getPackageDetailsByPackageId(id)).thenThrow(IdNotFoundException.class);
//
//		this.mockMvc.perform(get("/api/v2/package/{packageId}",id))
//			.andExpect(status().isNotFound());
//	}
//
//	@Test
//	void shouldUpdatePackageDetails() throws Exception{
//		Service service=new Service(1,"hair",3000);
//		Service service1=new Service(2,"nail",4000);
//		Service service2=new Service(3,"makeup",5000);
//		List<Service>services=new ArrayList<>();
//		services.add(service);
//		services.add(service1);
//		services.add(service2);
//		PackageDto packageDto = new PackageDto(101, "Bridal", "for marriages", services, 20000, null);
//		long id =101;
//		when(packageService.getPackageDetailsByPackageId(id)).thenReturn(packageDto);
//		when(packageService.updatePackageServiceById(id, packageDto)).thenReturn(packageDto);
//		mockMvc.perform(put("/api/v2/package/{packageId}",id)
//		        .contentType("application/json")
//		        .content(objectMapper.writeValueAsString(packageDto)))
//		        .andExpect(status().isOk());
//	}
//
//

}
