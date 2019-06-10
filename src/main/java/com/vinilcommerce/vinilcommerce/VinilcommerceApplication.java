package com.vinilcommerce.vinilcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.vinilcommerce")
@EnableAutoConfiguration
@EntityScan("com.vinilcommerce.model")
@EnableJpaRepositories("com.vinilcommerce.repository")
public class VinilcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinilcommerceApplication.class, args);
	}

}
