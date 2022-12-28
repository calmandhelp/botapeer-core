package com.ryokujun.controller.error;

import java.util.HashMap;
import java.util.List;

import lombok.Value;

@Value
public class ResponseError {
	private int status;
	private List<HashMap<String, String>> errors;

	public ResponseError(int status, List<HashMap<String, String>> errors) {
		this.status = status;
		this.errors = errors;
	}

}
