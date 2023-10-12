package com.stackroute.paymentservice.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrotendController {

	@RequestMapping(path = "/home")
	public String home() {
		System.out.println("In Home Controller Method");
		return "payment";
	}

	@GetMapping("/action")
	public String index() {
		return "index";
	}

}
