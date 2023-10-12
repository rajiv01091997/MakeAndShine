package com.stackroute.wishlistservice.service;

import com.stackroute.wishlistservice.dto.WishListDto;
import com.stackroute.wishlistservice.entity.Package;
import com.stackroute.wishlistservice.entity.Services;

public interface IWishlistService {
	
	//Method for create a WishList
	WishListDto createWishList(String emailid);
	
	
	//Method for Adding a Services in WishList
	WishListDto addServcie(String emailid, Services services);
	
	
	//Method for Adding a packages in WishList
	WishListDto addPackage(String email , Package packages);
	
	//Method for  Getting the data of WishList
	WishListDto getWishListbyId(String emailid);
	
	
	//Method for Deleting the services from WishList
	void deleteService(String emailid , long serviceId);
	
	
	//Method for Deleting the package from WishList
	void deletePackage(String emailid, long packageId);

//WishListDto addWishList(WishListDto wishlistdto);
	
	
}
