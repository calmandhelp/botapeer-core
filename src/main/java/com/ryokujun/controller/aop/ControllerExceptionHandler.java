package com.ryokujun.controller.aop;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	//	@ExceptionHandler(ValidationException.class)
	//	public ResponseEntity<Object> handleMyException(ValidationException ex, WebRequest request) {
	//		ResponseError re = new ResponseError("500", ex.getMessage());
	//		return super.handleExceptionInternal(ex, re, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	//	}

}
