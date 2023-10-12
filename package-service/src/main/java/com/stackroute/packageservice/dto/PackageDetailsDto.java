package com.stackroute.packageservice.dto;



public class PackageDetailsDto {
	
	private String packageName;
    private String packageDescription;
    
    
    
	public PackageDetailsDto() {
		super();
	}
	public PackageDetailsDto(String packageName, String packageDescription) {
		super();
		this.packageName = packageName;
		this.packageDescription = packageDescription;
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
    
    

}
