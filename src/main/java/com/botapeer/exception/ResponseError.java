package com.botapeer.exception;

import java.util.HashMap;
import java.util.List;

import lombok.Value;

@Value
public class ResponseError {
	private int status;
	private List<HashMap<String, Object>> errors;

	public ResponseError(int status, List<HashMap<String, Object>> errors) {
		this.status = status;
		this.errors = errors;
	}

}
