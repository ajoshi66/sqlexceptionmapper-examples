package com.joshi.vehicles.repos;

import org.springframework.data.repository.CrudRepository;

import com.joshi.vehicles.entities.Manufacturer;

public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {

}
