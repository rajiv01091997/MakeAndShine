package com.stackroute.packageservice.dto;

import java.util.Arrays;
import java.util.List;



import com.stackroute.packageservice.model.Service;

import lombok.Data;

@Data

public class PackageDto {
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
	public PackageDto(long packageId, String packageName, String packageDescription, List<Service> services,
			double packageAmount, byte[] packageImage) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.services = services;
		this.packageAmount = packageAmount;
		this.packageImage = packageImage;
	}
	public PackageDto() {
		super();
	}
	@Override
	public String toString() {
		return "PackageDto [packageId=" + packageId + ", packageName=" + packageName + ", packageDescription="
				+ packageDescription + ", services=" + services + ", packageAmount=" + packageAmount + ", packageImage="
				+ Arrays.toString(packageImage) + "]";
	}
    
}

