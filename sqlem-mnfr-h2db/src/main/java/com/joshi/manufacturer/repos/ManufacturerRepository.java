package com.joshi.manufacturer.repos;

import org.springframework.data.repository.CrudRepository;

import com.joshi.manufacturer.entities.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
	
//	@InitBinder
//	public default void initBinder(WebDataBinder binder) {
//		System.out.println("Adding validator to binder...");
//		binder.addValidators(new ManufacturerValidator());
//	}

}
