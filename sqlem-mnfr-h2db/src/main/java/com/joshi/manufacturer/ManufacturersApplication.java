package com.joshi.manufacturer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ManufacturersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManufacturersApplication.class, args);
	}
	
//	@Bean
//	public ManufacturerValidator beforeCreateManufacturerValidator() {
//		log.info("Creating Manufacturer Validator...");
//	    return new ManufacturerValidator();
//	}

}
