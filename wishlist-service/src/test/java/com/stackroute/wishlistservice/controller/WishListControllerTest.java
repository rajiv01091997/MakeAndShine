package com.stackroute.wishlistservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.wishlistservice.dto.WishListDto;
import com.stackroute.wishlistservice.entity.Package;
import com.stackroute.wishlistservice.entity.Services;
import com.stackroute.wishlistservice.entity.WishList;
import com.stackroute.wishlistservice.repository.WishListRepository;
import com.stackroute.wishlistservice.service.IWishlistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WishListController.class)
@ActiveProfiles("controller")

public class WishListControllerTest {

	
	
	@Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 private IWishlistService wishlistService;
	 
	 @MockBean
	 private WishListRepository bookRepository;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @MockBean
		private ModelMapper modelmapper;
	 
	 
	 @Test
		 public void contextLoads() {

		}
	 
	 
	 @Test
	public void createWishList() throws Exception{
		 String emailid = "prabhatkumar@gmail.com";
			List<Services> serviceList = new ArrayList<>();
			List<Package> packageList = new ArrayList<>();

			WishList wishlist = new WishList();
			wishlist.setEmailid(emailid);
			wishlist.setServices(serviceList);
			wishlist.setPackages(packageList);
			wishlist.setAmount(1230);
		 WishListDto toDto = this.userToDto(wishlist);
		
         when(wishlistService.createWishList(emailid)).thenReturn(toDto);
         mockMvc.perform(post("/api/v2//wishlist/{emailid}",emailid)
 		        .contentType("Text")
 		        .content(objectMapper.writeValueAsString(wishlist)))
 		        .andExpect(status().isOk());
		 
	 }
	 
	 @Test
	public void addService() throws Exception{
		 String emailid = "prabhatkumar@gmail.com";
			List<Services> serviceList = new ArrayList<>();
			List<Package> packageList = new ArrayList<>();
		Services	services = new Services();
			serviceList.add(services) ;
			WishList wishlist = new WishList();
			wishlist.setEmailid(emailid);
			wishlist.setServices(serviceList);
			wishlist.setPackages(packageList);
			wishlist.setAmount(1230);
		 WishListDto toDto = this.userToDto(wishlist);
		
         when(wishlistService.addServcie(emailid, services)).thenReturn(toDto);
         mockMvc.perform(post("/api/v2//wishlist/service/{emailid}",emailid)
 		        .contentType("application/json")
 		        .content(objectMapper.writeValueAsString(services)))
 		        .andExpect(status().isOk());
		 
	 }
	 
	 
	 @Test
	public void addPackage() throws Exception{
		 String emailid = "prabhatkumar@gmail.com";
			List<Services> serviceList = new ArrayList<>();
			List<Package> packageList = new ArrayList<>();
		Package	packages = new Package();
		packageList.add(packages);
			WishList wishlist = new WishList();
			wishlist.setEmailid(emailid);
			wishlist.setServices(serviceList);
			wishlist.setPackages(packageList);
			wishlist.setAmount(1230);
		 WishListDto toDto = this.userToDto(wishlist);
		
         when(wishlistService.addPackage(emailid, packages)).thenReturn(toDto);
         mockMvc.perform(post("/api/v2//wishlist/package/{emailid}",emailid)
 		        .contentType("application/json")
 		        .content(objectMapper.writeValueAsString(packages)))
 		        .andExpect(status().isOk());
		 
	 }


	@Test
	 public void getWishListById() throws Exception{
		final String emailid = "rahul";
		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		Package	packages = new Package();
		packageList.add(packages);
		WishList wishlist = new WishList();
		wishlist.setEmailid(emailid);
		wishlist.setServices(serviceList);
		wishlist.setPackages(packageList);
		wishlist.setAmount(1230);
		WishListDto toDto = this.userToDto(wishlist);


		when(wishlistService.getWishListbyId(emailid)).thenReturn(toDto);

		this.mockMvc.perform(get("/api/v2/wishlist/{emailId}",emailid))
				.andExpect(status().isOk());
	}





	 
	 

		public WishList dtoToWishList(WishListDto wishlistdto) {
			WishList wishlist = new ModelMapper().map(wishlistdto, WishList.class);

			return wishlist;
		}

		/*
		 * Method to convert entity to dto
		 */

		public WishListDto userToDto(WishList wishlist) {
			WishListDto wishlistdto = new ModelMapper().map(wishlist, WishListDto.class);

			return wishlistdto;
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
