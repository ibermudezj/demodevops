package com.tecnicaltest.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleApiResponse {
	
	protected Boolean success;
	
	protected String message;

	@JsonCreator
	public SimpleApiResponse(@JsonProperty("success")Boolean success, @JsonProperty("message")String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
