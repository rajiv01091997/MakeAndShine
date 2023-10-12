package com.stackroute.paymentservice.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class PaymentInfoModel {
	
	private String paymentId;
	private String modeOfPayment;
	private BigInteger amount;
	private String serviceName;
	private int serviceId;
	private String packageName;
	private int packageId;

	@Id
	private int appointmentId;
	
	private String customerEmail;
	private String paymentStatus;
	
	
	
	public PaymentInfoModel(String paymentId, String modeOfPayment, BigInteger amount, String serviceName,
			int serviceId, String packageName, int packageId, int appointmentId, String customerEmail,
			String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.modeOfPayment = modeOfPayment;
		this.amount = amount;
		this.serviceName = serviceName;
		this.serviceId = serviceId;
		this.packageName = packageName;
		this.packageId = packageId;
		this.appointmentId = appointmentId;
		this.customerEmail = customerEmail;
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String razorpayOrderId) {
		this.paymentId = razorpayOrderId;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentInfoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PaymentInfoModel [paymentId=" + paymentId + ", modeOfPayment=" + modeOfPayment + ", amount=" + amount
				+ ", serviceName=" + serviceName + ", serviceId=" + serviceId + ", packageName=" + packageName
				+ ", packageId=" + packageId + ", appointmentId=" + appointmentId + ", customerEmail=" + customerEmail
				+ ", paymentStatus=" + paymentStatus + "]";
	}	

	

}
