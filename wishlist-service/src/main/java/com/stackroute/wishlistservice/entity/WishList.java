package com.stackroute.wishlistservice.entity;

import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;



@Data
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "WishList")

public class WishList {

	@Id
  @Email
	private String emailid;
	private List<Services> services;
	private List<Package> packages;
	private double amount;


}
