package com.qa.gorest.constants;

public enum APIHttpStatus {
	OK_200(200,"OK"),
	CREATED_201(201,"CREATED");

	
	private final int code;
	private final String message;
	
	APIHttpStatus(int code,String message){
		this.code=code;
		this.message=message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	
	@Override
	public String toString() {
		return code + " "+ message; 
	}
}
