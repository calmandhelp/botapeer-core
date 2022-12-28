package com.ryokujun.controller.error;

import lombok.Value;

@Value
public class ResponseError {
	private String status;
	private String message;

	public ResponseError(String status, String message) {
		this.status = status;
		this.message = message;
	}

	//	public ResponseEntity<ResponseError> createResponse(HttpStatus status) {
	//		return new ResponseEntity<ResponseError>(this, status);
	//	}
	//
	//	public static ResponseEntity<ResponseError> createResponse(ValidationException e) {
	//		return new ResponseEntity<ResponseError>(
	//				new ResponseError(e.get, e.getMessage()),HttpStatus.BAD_REQUEST));
	//	}
}
