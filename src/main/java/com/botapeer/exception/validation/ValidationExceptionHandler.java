package com.botapeer.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.botapeer.exception.ErrorMessages;
import com.botapeer.exception.ResponseError;

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
		ValidationExceptionDto dto = new ValidationExceptionDto();
		ErrorMessages errorMessages = dto.ValidationCodeConverter(ex.getErrorMessages());
		ResponseError re = new ResponseError(HttpStatus.BAD_REQUEST.value(), errorMessages.getMessages());

		return super.handleExceptionInternal(ex, re, null, HttpStatus.BAD_REQUEST, request);
	}

}
