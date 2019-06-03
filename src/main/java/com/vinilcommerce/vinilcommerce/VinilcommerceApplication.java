package com.vinilcommerce.vinilcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.vinilcommerce.model")
@EnableJpaRepositories("com.vinilcommerce.repository")
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
