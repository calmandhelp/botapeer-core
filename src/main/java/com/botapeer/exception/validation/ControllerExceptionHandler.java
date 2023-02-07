package com.botapeer.exception.validation;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
		ResponseDto dto = new ResponseDto();
		List<HashMap<String, String>> response = dto.ValidationErrorToResponse(ex.getErrorMessages());
		ResponseError re = new ResponseError(HttpStatus.BAD_REQUEST.value(), response);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.BAD_REQUEST, request);
	}

}
