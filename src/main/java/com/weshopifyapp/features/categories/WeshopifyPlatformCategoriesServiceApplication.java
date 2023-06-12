package com.weshopifyapp.features.categories;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeshopifyPlatformCategoriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyPlatformCategoriesServiceApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
