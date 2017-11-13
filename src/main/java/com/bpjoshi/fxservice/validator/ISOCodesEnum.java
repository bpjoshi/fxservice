package com.bpjoshi.fxservice.validator;

public enum ISOCodesEnum {
	
	USD_ISO("USD"),
	EURO_ISO("EUR");
	
	private String value;
	 public String getValue() {
	    return value;
	  }
	 
	 private ISOCodesEnum(String value) {
	  this.value = value;
	 } 
}
