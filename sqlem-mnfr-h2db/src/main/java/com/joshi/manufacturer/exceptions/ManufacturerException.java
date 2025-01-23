package com.joshi.manufacturer.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ManufacturerException extends Exception {
	
	private String code;
	private Object[] errorParams;
	private HttpStatus httpStatus;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ManufacturerException( String message ) {
		super(message);
	}

	public ManufacturerException( String message, Throwable t ) {
		super(message, t);
	}

	public ManufacturerException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ManufacturerException(String code, String message, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public ManufacturerException(String code, String message, Object...objects ) {
		super(message);
		this.code = code;
		this.errorParams = objects;
	}

	public ManufacturerException(String code, String message, HttpStatus httpStatus, Object...objects ) {
		super(message);
		this.code = code;
		this.errorParams = objects;
		this.httpStatus = httpStatus;
	}
	
}
