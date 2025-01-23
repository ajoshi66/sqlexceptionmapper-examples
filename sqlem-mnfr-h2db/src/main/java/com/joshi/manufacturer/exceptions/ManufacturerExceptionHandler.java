package com.joshi.manufacturer.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ManufacturerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ManufacturerException.class)
    public ResponseEntity<?> handleApplicationException(
            final ManufacturerException exception, final HttpServletRequest request) {
		log.error("Something went wrong:" + exception.getMessage());
		String message = exception.getMessage();
		if (exception.getCode() != null) {
			message = messageSource.getMessage(exception.getCode(), exception.getErrorParams(), request.getLocale());
		}
		HttpStatus httpStatus = exception.getHttpStatus();
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(message, httpStatus);
	}
	
}
