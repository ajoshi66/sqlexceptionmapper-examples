package com.joshi.vehicles.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class VehicleException extends Exception {

	private ExceptionCodes exceptionCode;
	private Object[] params;
	private HttpStatus httpStatus;
	
	public VehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public VehicleException(String message, Throwable cause) {
		super(message, cause);
	}
	public VehicleException(String message) {
		super(message);
	}
	public VehicleException(Throwable cause) {
		super(cause);
	}
	
	public VehicleException(ExceptionCodes code, String message) {
		super(message);
		this.exceptionCode = code;
	}
	
	public VehicleException(ExceptionCodes code, String message, Object... params) {
		super(message);
		this.exceptionCode = code;
		this.params = params;
	}
	
	public VehicleException(ExceptionCodes code, String message, HttpStatus httpStatus) {
		super(message);
		this.exceptionCode = code;
		this.httpStatus = httpStatus;
	}
	
	public VehicleException(ExceptionCodes code, String message, HttpStatus httpStatus, Object... params) {
		super(message);
		this.exceptionCode = code;
		this.params = params;
		this.httpStatus = httpStatus;
	}
	
	public VehicleException(ExceptionCodes code, String message, Throwable cause) {
		super(message, cause);
		this.exceptionCode = code;
	}
	
	public VehicleException(ExceptionCodes code, String message, Throwable cause, Object... params) {
		super(message, cause);
		this.exceptionCode = code;
		this.params = params;
	}
	
	public VehicleException(ExceptionCodes code, String message, HttpStatus httpStatus, Throwable cause) {
		super(message, cause);
		this.exceptionCode = code;
		this.httpStatus = httpStatus;
	}
	
	public VehicleException(ExceptionCodes code, String message, HttpStatus httpStatus, Throwable cause, Object... params) {
		super(message, cause);
		this.exceptionCode = code;
		this.params = params;
		this.httpStatus = httpStatus;
	}
	
	public String getCode() {
		String result = null;
		if (this.exceptionCode != null) result = this.exceptionCode.getCode();
		return result;
	}

}
