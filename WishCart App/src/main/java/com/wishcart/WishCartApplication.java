package com.wishcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(allowedHeaders = "*")
public class WishCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishCartApplication.class, args);
	}

}
