package com.stackroute.paymentservice.entity;

public class PaymentResponse {
	String secretKey;
	public String razorpayOrderId;
	String aplicationFee;
	String secretId;
	String pgName;
	
	@Override
	public String toString() {
		return "OrderResponse [secretKey=" + secretKey + ", razorpayOrderId=" + razorpayOrderId + ", aplicationFee="
				+ aplicationFee + ", secretId=" + secretId + ", pgName=" + pgName + "]";
	}
	public PaymentResponse() {
		super();
	}
	public PaymentResponse(String secretKey, String razorpayOrderId, String aplicationFee, String secretId,
			String pgName) {
		super();
		this.secretKey = secretKey;
		this.razorpayOrderId = razorpayOrderId;
		this.aplicationFee = aplicationFee;
		this.secretId = secretId;
		this.pgName = pgName;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getAplicationFee() {
		return aplicationFee;
	}
	public void setAplicationFee(String aplicationFee) {
		this.aplicationFee = aplicationFee;
	}
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

}
