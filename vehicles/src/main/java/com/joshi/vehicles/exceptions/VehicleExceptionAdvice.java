package com.joshi.vehicles.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class VehicleExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msgs;
	
	@ExceptionHandler(VehicleException.class)
	public ResponseEntity<?> handleApplicationException(
			final VehicleException ve, final HttpServletRequest req) {
		String msg = ve.getMessage();
		try {
			msg = msgs.getMessage(ve.getCode(), ve.getParams(), req.getLocale());
		} catch (NoSuchMessageException e) {
			//Do nothing. use ve.getMessage()
		}
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (ve.getHttpStatus() != null) {
			status = ve.getHttpStatus();
		}
		return new ResponseEntity<>("Error occured:" + msg, status);
	}

}
