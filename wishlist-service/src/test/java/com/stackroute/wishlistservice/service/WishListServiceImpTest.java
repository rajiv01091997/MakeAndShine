package com.stackroute.wishlistservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.wishlistservice.dto.WishListDto;
import com.stackroute.wishlistservice.entity.Services;
import com.stackroute.wishlistservice.entity.Package;
import com.stackroute.wishlistservice.entity.WishList;
import com.stackroute.wishlistservice.repository.WishListRepository;

@SpringBootTest
public class WishListServiceImpTest {
	
	
	@Autowired
	private IWishlistService wishlistService;

	@MockBean
	private WishListRepository wishListRepository;

	@MockBean
	private ModelMapper modelmapper;

	// private WishList wishList;

	@Test
	void contextLoads() {

	}

	@Test
	public void createWishlistTest() {
		String emailid = "ram";
		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();

		WishList wishlist = new WishList();
		wishlist.setEmailid(emailid);
		wishlist.setServices(serviceList);
		wishlist.setPackages(packageList);
		wishlist.setAmount(0);
		System.out.println(wishlist);

		WishListDto wishlistdto = this.userToDto(wishlist);

		when(wishListRepository.insert(wishlist)).thenReturn(wishlist);
		when(wishlistService.createWishList(emailid)).thenReturn(wishlistdto);

		assertEquals(wishlist.getEmailid(), wishlistdto.getEmailid());

	}

	@Test
	public void addServiceTest() {
		WishList wishList = new WishList();
		String emailid = "Ram";
		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		Services services = new Services(12, "service1", 2334, null);
		wishList.setEmailid("ram");
		wishList.setServices(serviceList);
		wishList.setPackages(packageList);
		;
		wishList.setAmount(100);

		WishListDto wishlistdto = this.userToDto(wishList);

		when(wishListRepository.findById(emailid)).thenReturn(Optional.of(wishList));

		when(wishListRepository.save(wishList)).thenReturn(wishList);
		when(wishlistService.addServcie(emailid, services)).thenReturn(wishlistdto);
		 WishListDto wishListDto2 =wishlistService.addServcie(emailid, services);
		assertEquals(wishList.getEmailid(), wishListDto2.getEmailid());

	}

	@Test
	public void getWishListbyIdTest() {
		String emailid = "ram@gmail.com";
		WishList wishList = new WishList();
		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		wishList.setEmailid("ram");
		wishList.setServices(serviceList);
		wishList.setPackages(packageList);
		;
		wishList.setAmount(100);
		WishListDto wishlistdto = this.userToDto(wishList);

		when(wishListRepository.findById(emailid)).thenReturn(Optional.of(wishList));
		when(wishlistService.getWishListbyId(emailid)).thenReturn(wishlistdto);
		WishListDto dto = wishlistService.getWishListbyId(emailid);

		assertEquals(dto.getEmailid(), wishList.getEmailid());

	}

	@Test
	public void addPackageTest() {
		WishList wishList = new WishList();
		String emailid = "ram";
		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		Package packages = new Package(12, "ram", 2334, null);
		wishList.setEmailid("ram");
		wishList.setServices(serviceList);
		wishList.setPackages(packageList);
		;
		wishList.setAmount(100);

		WishListDto wishlistdto = this.userToDto(wishList);

		when(wishListRepository.findById(emailid)).thenReturn(Optional.of(wishList));

		when(wishListRepository.save(wishList)).thenReturn(wishList);
		when(wishlistService.addPackage(emailid, packages)).thenReturn(wishlistdto);

		assertEquals(wishList.getEmailid(), wishlistdto.getEmailid());
		assertEquals(wishList.getServices(), wishlistdto.getServices());

	}

	@Test
	public void removeServiceTest() {

		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		WishList wishList = new WishList("ram@gmail.com", serviceList, packageList, 12222);
		Services services = new Services(120, "ram", 2334, null);
		serviceList.add(services);
		when(wishListRepository.save(wishList)).thenReturn(wishList);

		when(wishListRepository.findById("ram")).thenReturn(Optional.of(wishList));

		wishlistService.deleteService("ram", 101);

		WishList wishlist2 = wishList;

		List<Services> serviceList2 = new ArrayList<>();

		wishlist2.setServices(serviceList2);

		when(wishListRepository.save(wishlist2)).thenReturn(wishlist2);

		assertEquals(0, wishListRepository.findById("ram").get().getServices().size());

	}

	@Test
	public void removePackageTest() {

		List<Services> serviceList = new ArrayList<>();
		List<Package> packageList = new ArrayList<>();
		WishList wishList = new WishList("ram@gmail.com", serviceList, packageList, 12222);
		Package packages = new Package(110, "syam", 2334, null);
		packageList.add(packages);
		when(wishListRepository.save(wishList)).thenReturn(wishList);

		when(wishListRepository.findById("syam")).thenReturn(Optional.of(wishList));

		wishlistService.deletePackage("syam", 110);
		WishList wishlist2 = wishList;

		List<Package> packageList1 = new ArrayList<>();

		wishlist2.setPackages(packageList1);

		when(wishListRepository.save(wishlist2)).thenReturn(wishlist2);

		assertEquals(0, wishListRepository.findById("syam").get().getPackages().size());
	}

	/*
	 * Method to convert entity to dto
	 */

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
