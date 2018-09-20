package com.tecnicaltest.dto.response;

public class ApiResponse<T> extends SimpleApiResponse {


	private T payload;

	public ApiResponse() {
		super(null, null);
	}
	
	@SuppressWarnings("unchecked")
	public ApiResponse(Boolean success, String message, Object payload) {
		super(success, message);
		this.payload = (T)payload;
	}
	
	@SuppressWarnings("unchecked")
	public ApiResponse(Object payload) {
		super(true, "");
		this.payload = (T)payload;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	
	
}
