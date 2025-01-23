package com.joshi.manufacturer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshi.manufacturer.exceptions.ManufacturerException;
import com.joshi.manufacturer.model.Manufacturer;
import com.joshi.manufacturer.repos.ManufacturerRepository;
import com.joshi.manufacturer.repos.ManufacturerValidator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/manufacturers")
@Slf4j
public class ManufacturerController {
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private ManufacturerRepository mnfrRepo;
	
	@Autowired
	private ManufacturerValidator mnfrValidator;
	
	@GetMapping("/health")
	public ResponseEntity<?> checkHealth() {
		log.info("Checking health of manufacturers controller...");
		log.info("Repository status:" + ((mnfrRepo != null)? "Good" : "Not OK"));
		log.info("DB status:" + ((mnfrRepo.count() >= 0)? "Good" : "Not OK"));
		return ResponseEntity.ok("Manufacturer is running fine.");
	}
	
	@GetMapping
	public List<Manufacturer> getManufacturers() {
		log.info("Getting the list of manufacturers...");
		Iterable<com.joshi.manufacturer.entities.Manufacturer> mnfrs = mnfrRepo.findAll();
		List<Manufacturer> mnfrList = new ArrayList<Manufacturer>();
		mnfrs.forEach(m -> mnfrList.add(m.toManufacturer()));
		return mnfrList;
	}
	
	@GetMapping("/{mnfrId}")
	public Manufacturer getManufacturer(@PathVariable Long mnfrId, HttpServletRequest request) throws ManufacturerException {
		log.info("Getting the manufacturer with id {}", mnfrId);
		Errors errors = new SimpleErrors(mnfrId);
		mnfrValidator.validateGet(mnfrId, errors);
		buildException(errors, request.getLocale());
		Optional<com.joshi.manufacturer.entities.Manufacturer> mnfrEntity = mnfrRepo.findById(mnfrId);
		Manufacturer mnfr = null;
		if (mnfrEntity.isEmpty()) {
			throw new ManufacturerException("101", "Manufacturer with Id {0} is not found.", HttpStatus.BAD_REQUEST, mnfrId);
		} else {
			mnfr = mnfrEntity.get().toManufacturer();
		}
		return mnfr;
	}
	
	@PostMapping()
	public ManufacturerResponse addManufacturer(@RequestBody Manufacturer mnfr, HttpServletRequest request) throws ManufacturerException {
		log.info("Inserting the manufacturer...");
//		Errors errors = new SimpleErrors(mnfr);
//		mnfrValidator.validateInsert(mnfr, errors);
//		buildException(errors, request.getLocale());
		com.joshi.manufacturer.entities.Manufacturer mnfrEntity = new com.joshi.manufacturer.entities.Manufacturer(mnfr);
		mnfrEntity = mnfrRepo.save(mnfrEntity);
		ManufacturerResponse resp = new ManufacturerResponse();
		resp.setManufacturer(mnfrEntity.toManufacturer());
		//resp.setLogMessges(rlog.getMessages());
		return resp;
	}
	
	
	private void buildException(Errors errors, Locale locale) throws ManufacturerException {
		StringBuilder msgBldr = new StringBuilder();
		errors.getAllErrors().forEach(m -> {
			log.info("Exception message: " + m.getCode() + "|" + m.getDefaultMessage());
			msgBldr.append(messageSource.getMessage(m.getCode(), null, m.getDefaultMessage(), locale))
				.append("\n");
		});
		if (msgBldr.length() > 0) {
			msgBldr.deleteCharAt(msgBldr.length() - 1);
			throw new ManufacturerException(msgBldr.toString());
		}
	}
	
}
