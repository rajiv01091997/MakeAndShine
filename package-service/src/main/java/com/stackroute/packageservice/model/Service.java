package com.stackroute.packageservice.model;



public class Service {
	private long serviceId;
	private String serviceName;
	private double amount;
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Service(long serviceId, String serviceName, double amount) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.amount = amount;
	}
	public Service() {
		super();
	}
	public double getAmount() {
		return amount;
	}
	
}