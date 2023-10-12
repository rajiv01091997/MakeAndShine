package com.stackroute.wishlistservice.service;

import java.util.*;

import com.stackroute.wishlistservice.Exception.IdNotFoundException;
import com.stackroute.wishlistservice.entity.Package;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.wishlistservice.dto.WishListDto;
import com.stackroute.wishlistservice.entity.WishList;
import com.stackroute.wishlistservice.repository.WishListRepository;

import com.stackroute.wishlistservice.entity.Services;

import org.springframework.stereotype.Service;

@Service

public class WishListServiceImpl implements IWishlistService {

	@Autowired
	private WishListRepository wishlistRepository;
	
	@Autowired
	private ModelMapper modelmapper;

	/*
	 @Description : this method is used to Add the WishList
	  @Param:
	  @returns : It returns the entity class object 
      @throws  
	 @Created by : Prabhat
      @createdDate : 17 November 2022  
	 */
	
	
	@Override
	public WishListDto createWishList(String emailid) {

		List<Services> serviceList = new ArrayList<>();

		List<Package> packageList = new ArrayList<>();

		WishList wishlist = new WishList();

		wishlist.setEmailid(emailid);

		wishlist.setServices(serviceList);

		wishlist.setPackages(packageList);
	
		wishlist.setAmount(0);
		
		return this.userToDto(wishlistRepository.insert(wishlist));
	}
	
	
	
	
	

	@Override
	public WishListDto addServcie(String emailid,Services services) {
		
		List<Services> serviceList = new ArrayList<>();
		  serviceList.add(services);
		WishList wishList = wishlistRepository.findById(emailid).get();
		if(wishList.getEmailid()==emailid){
			throw new IdNotFoundException("WishLIst not found  with ID : " + emailid);
		}else {

			wishList.getServices().addAll(serviceList);
			double price =wishList.getAmount()+services.servicePrice;
			wishList.setAmount(price);
			wishList = wishlistRepository.save(wishList);

			return this.userToDto(wishList);

		}

	}
	
	
	
	@Override
	public WishListDto addPackage(String emailid, Package packages) {

		 WishList wishList = wishlistRepository.findById(emailid).get();
		 wishList.getPackages().add(packages);
		if(!Objects.equals(wishList.getEmailid(), emailid)){
			throw new IdNotFoundException("WishLIst not found  with ID : " + emailid);
		}else {

			double price = wishList.getAmount() + packages.packagePrice;
			wishList.setAmount(price);
			wishList = wishlistRepository.save(wishList);
			return this.userToDto(wishList);
		}
	}
	
	
	

	@Override
	public WishListDto getWishListbyId(String emailid) {
		WishList wishList = wishlistRepository.findById(emailid).get();
		if (wishList.getEmailid() == emailid) {
			throw new IdNotFoundException("WishLIst not found  with ID : " + emailid);
		} else {

			return this.userToDto(wishList);
		}
	}

	@Override
	public void deleteService(String emailid, long serviceId)  {

		double serviceAmount = 0;

		WishList wishlist = wishlistRepository.findById(emailid).get();

		for (Iterator<Services> iterator = wishlist.getServices().iterator(); iterator.hasNext(); ) {
			Services s = iterator.next();
			if (s.serviceId == serviceId) {
				serviceAmount += s.servicePrice;

				iterator.remove();
			}
		}
			double price=	wishlist.getAmount()-serviceAmount;
			wishlist.setAmount(price);
			wishlistRepository.save(wishlist);
		}

	@Override
	public void deletePackage(String emailid, long packageId) {
	 double packageAmount =0;
		WishList wishlist = wishlistRepository.findById(emailid).get();


		for (Iterator<Package> iterator = wishlist.getPackages().iterator(); iterator.hasNext(); ) {
			Package aPackage = iterator.next();
			if (aPackage.packageId == packageId) {
				packageAmount += aPackage.packagePrice;

				iterator.remove();
			}
		}
		double price=	wishlist.getAmount()-packageAmount;
		wishlist.setAmount(price);
		wishlistRepository.save(wishlist);

	}


	/*
	 * Method to convert entity to dto
	 */

	public WishList dtoToWishList(WishListDto wishlistdto) {
		WishList wishlist = this.modelmapper.map(wishlistdto, WishList.class);

		return wishlist;
	}

	/*
	 * Method to convert entity to dto
	 */

	public WishListDto userToDto(WishList wishlist) {
		WishListDto wishlistdto = this.modelmapper.map(wishlist, WishListDto.class);

		return wishlistdto;
	}

}
