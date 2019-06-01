package com.vinilcommerce.vinilcommerce;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VinilcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinilcommerceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			System.out.println("Inserindo...");
//			Product them = new Product("Them", "Metal", new BigDecimal(50), BigDecimal.TEN);
//			
//			System.out.println(them);
//		};
//	}
	
}
