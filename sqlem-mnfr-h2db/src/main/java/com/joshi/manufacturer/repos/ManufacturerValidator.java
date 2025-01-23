package com.joshi.manufacturer.repos;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.joshi.manufacturer.model.Manufacturer;

import lombok.extern.slf4j.Slf4j;

@Component //("beforeCreateManufacturerValidator")
@Slf4j
public class ManufacturerValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("Checking for class: " + clazz);
		return Manufacturer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		errors.reject("000", "Invoking validation via Spring is not enabled.");
	}
	
	public void validateGet(Object target, Errors errors) {
		log.info("Validating Get...");
		if (target instanceof Long id) {
			if (id == null || id <=0) {
				errors.reject("102", "Manufacturer Id must be greater than 0");
			}
		}
		
	}
	
	public void validateInsert(Manufacturer mnfr, Errors errors) {
		log.info("Validating Insert...");
		if (mnfr == null) {
			errors.reject("id", "110");
		} else {
			if (mnfr.id() != null) errors.rejectValue("id", "103");
			isBlank(mnfr.manufacturerName(), errors, "manufacturerName", "104");
			isBlank(mnfr.address1(), errors, "address1", "105");
			isBlank(mnfr.city(), errors, "city", "106");
			isBlank(mnfr.state(), errors, "state", "107");
			isBlank(mnfr.country(), errors, "country", "108");
		}
	}

//	public void validatePatch(Object target, Errors errors) {
//		log.info("Validating Patch...");
//		int fldCount = 0;
//		if (target instanceof Manufacturer mnfr) {
//			if (mnfr != null) {
//				fldCount += isBlankPatch(mnfr.manufacturerName(), errors, "manufacturerName", "104");
//				fldCount += isBlankPatch(mnfr.address1(), errors, "address1", "105");
//				fldCount += (mnfr.address2() != null)? 1 : 0;
//				fldCount += isBlankPatch(mnfr.city(), errors, "city", "106");
//				fldCount += isBlankPatch(mnfr.state(), errors, "state", "107");
//				fldCount += isBlankPatch(mnfr.country(), errors, "country", "108");
//			}
//		}
////		if (fldCount == 0) {
////			errors.reject("109", "Nothing updated");
////		}
//		
//	}
	
	public void validatePatch(Map<String, Object> mnfrMap, Errors errors) {
		log.info("Validating Patch...");
		if (mnfrMap.containsKey("manufacturerName")) isBlank((String) mnfrMap.get("manufacturerName"), errors, "manufacturerName", "104");
		if (mnfrMap.containsKey("address1")) isBlank((String) mnfrMap.get("address1"), errors, "address1", "105");
		if (mnfrMap.containsKey("city")) isBlank((String) mnfrMap.get("city"), errors, "city", "106");
		if (mnfrMap.containsKey("state")) isBlank((String) mnfrMap.get("state"), errors, "state", "107");
		if (mnfrMap.containsKey("country")) isBlank((String) mnfrMap.get("country"), errors, "country", "108");
	
	}
	
	private void isBlank(String val, Errors err, String fld, String msg) {
		if (val == null || val.isBlank()) err.rejectValue(fld, msg);
	}

//	private int isBlankPatch(String val, Errors err, String fld, String msg) {
//		int fldCount = 0;
//		if (val != null) {
//			isBlank(val, err, fld, msg);
//			fldCount ++;
//		}
//		return fldCount;
//	}
//
}
