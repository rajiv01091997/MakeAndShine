package com.stackroute.wishlistservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc

public class WishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper() {
			return new ModelMapper();
	}
}
