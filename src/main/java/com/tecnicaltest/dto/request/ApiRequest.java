package com.tecnicaltest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiRequest<T> {

	private T payload;
	
	public ApiRequest() {
		
	} 
	
	public ApiRequest( Object payload) {
		super();
		this.payload = (T)payload;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}
