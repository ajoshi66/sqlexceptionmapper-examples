package com.joshi.vehicles.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshi.vehicles.exceptions.ExceptionCodes;
import com.joshi.vehicles.exceptions.VehicleException;
import com.joshi.vehicles.model.Manufacturer;
import com.joshi.vehicles.repos.ManufacturerRepo;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
	
//	private static Map<Long, Manufacturer> mnfrStore = initMnfrStore();
	
	@Autowired
	private ManufacturerRepo mnfrRepo;
	
//	@GetMapping
//	public List<Manufacturer> getManufacturers() {
//		return new ArrayList<Manufacturer>(mnfrStore.values());
//	}

	@GetMapping
	public List<Manufacturer> getManufacturers() {
		List<Manufacturer> result = new ArrayList<Manufacturer>();
		mnfrRepo.findAll().forEach(m -> result.add(m.toModel()));
		return result;
	}

	@GetMapping("/{mnfrId}")
	public Manufacturer getManufacturer(@PathVariable Long mnfrId) throws VehicleException {
		Manufacturer result = null;
		if (mnfrId != null && mnfrId > 0) {
			Optional<com.joshi.vehicles.entities.Manufacturer> mnfrEntity = mnfrRepo.findById(mnfrId);
			if (!mnfrEntity.isEmpty()) {
				result = mnfrEntity.get().toModel();
			}
		}
		if (result == null) {
			throw new VehicleException(ExceptionCodes.MANUFACTURER_NOT_FOUND, "Manufacturer not found", HttpStatus.BAD_REQUEST, mnfrId);
		}
		return result;
	}

	
	@PostMapping
	public Manufacturer saveManufacturer(@RequestBody Manufacturer mnfr) throws VehicleException {
		Manufacturer result = null;
		if (mnfr != null) {
			com.joshi.vehicles.entities.Manufacturer mnfrEntity = mnfrRepo.save(new com.joshi.vehicles.entities.Manufacturer(mnfr));
			result = mnfrEntity.toModel();
		}
		return result;
	}

	
//	private static Map<Long, Manufacturer> initMnfrStore() {
//		Map<Long, Manufacturer> mnfrs = new HashMap<>();
//		mnfrs.put(101L, new Manufacturer(101L, "Maruthi Suzuki India Ltd.", "Old Palam Gurugram Road", null, "Gurugram", "Haryana", null));
//		mnfrs.put(102L, new Manufacturer(102L, "Tata Motors Ltd.", "24, Bombay House", "Homy Mody Street", "Mumbai", "Maharashtra", null));
//		return mnfrs;
//	}

}
