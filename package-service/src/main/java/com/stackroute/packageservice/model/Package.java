package com.stackroute.packageservice.model;


import java.util.Arrays;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
@Data
@ToString
@Setter
@Getter

@Document(collection = "package")
public class Package {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long packageId;
	private String packageName;
    private String packageDescription;
    private List<Service> services;
    private double packageAmount;
    private byte[] packageImage;
	public long getPackageId() {
		return packageId;
	}
	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public double getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(double packageAmount) {
		this.packageAmount = packageAmount;
	}
	public byte[] getPackageImage() {
		return packageImage;
	}
	public void setPackageImage(byte[] packageImage) {
		this.packageImage = packageImage;
	}
	public Package(long packageId, String packageName, String packageDescription, List<Service> services,
			double packageAmount, byte[] packageImage) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.services = services;
		this.packageAmount = packageAmount;
		this.packageImage = packageImage;
	}
	public Package() {
		super();
	}
	@Override
	public String toString() {
		return "Package [packageId=" + packageId + ", packageName=" + packageName + ", packageDescription="
				+ packageDescription + ", services=" + services + ", packageAmount=" + packageAmount + ", packageImage="
				+ Arrays.toString(packageImage) + "]";
	}
    
}

