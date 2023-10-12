package com.stackroute;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableEurekaClient
@EnableWebMvc
//@ComponentScan("com.stackroute.EmailServiceApplication")
@OpenAPIDefinition(info = @Info(title = "MakeandShine",description = "EmailService"))
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
