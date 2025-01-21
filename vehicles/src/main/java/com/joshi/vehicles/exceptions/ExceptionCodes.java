package com.joshi.vehicles.exceptions;

public enum ExceptionCodes {
	
	MANUFACTURER_NOT_FOUND("1001");

	private String code;
	
	ExceptionCodes(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
