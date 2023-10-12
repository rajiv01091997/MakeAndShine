package com.stackroute.paymentservice.service;

import java.math.BigInteger;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.entity.PaymentInfoModel;
import com.stackroute.paymentservice.entity.PaymentRequest;
import com.stackroute.paymentservice.entity.PaymentResponse;
import com.stackroute.paymentservice.repository.PaymentRepository;

@Service
public  class PaymentServiceImpl implements PaymentSrevice {

	private RazorpayClient client;

	private static final String SECRET_ID1 = "rzp_live_OclxmdwoLTP70";
	private static final String SECRET_KEY1 = "0a1TvbEaN7AimJdYAQi0zFgm";

	@Autowired
	public PaymentRepository repository;

	/**
	 * @description : save the payment Request
	 * @return : Returns payment response
	 */

	@Override
	public PaymentResponse savePaymentRequest(PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse = new PaymentResponse();
		try {
			client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
			Order createRazorPayOrder = createRazorPayOrder(paymentRequest.getAmount());

			String object = (String) createRazorPayOrder.get("id");

			paymentResponse.setSecretId(SECRET_ID1);
			paymentResponse.setSecretKey(SECRET_KEY1);
			paymentResponse.setRazorpayOrderId(object);
			paymentResponse.setAplicationFee("" + paymentRequest.getAmount());
			paymentResponse.setPgName("razor1");
			System.out.println("----orderid:" + object);

			PaymentInfoModel details = new PaymentInfoModel();
			details.setAmount(paymentRequest.getAmount());
			details.setCustomerEmail(paymentRequest.getEmail());
			details.setServiceName("service");
			details.setServiceId(0);
			details.setPackageName("package");
			details.setPackageId(0);
			details.setPaymentStatus("success");
			details.setAppointmentId(0);
			details.setModeOfPayment("Upi");
			details.setPaymentId(paymentResponse.razorpayOrderId);
			details.setAppointmentId(setID());
			repository.save(details);

			/*
			 * Random random = new Random(); int x = random.nextInt(100);
			 */

			System.out.println("Payment details saved with id " + details.getAppointmentId());

		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return (paymentResponse);

	}
   
	
	
	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1);
		return client.orders.create(options);
	}
	@Override
	public Optional<PaymentInfoModel> getPayment(int id) {
		System.out.println("Searching payment with id : " + id);
		System.out.println("DataBase: " + repository.findAll());
		return repository.findById(id);
	}

	@Override
	public Optional<PaymentInfoModel> updatePayment(int id, String status) {
		PaymentInfoModel updateInfo = repository.findById(id).orElseThrow();
		updateInfo.setPaymentStatus(status);
		repository.save(updateInfo);
		return Optional.ofNullable(updateInfo);
	}
	
	private int setID() {
		int id = (int) repository.count() + 1;
		return id;
	}
	
}