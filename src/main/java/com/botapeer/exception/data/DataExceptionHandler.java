package com.botapeer.exception.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.botapeer.exception.ErrorMessages;
import com.botapeer.exception.ResponseError;

@RestControllerAdvice
public class DataExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleValidationException(NotFoundException ex, WebRequest request) {
		DataExceptionDto dto = new DataExceptionDto();
		ErrorMessages errorMessages = dto.NotFoundErrorToResponse(ex.getErrorMessages());
		ResponseError re = new ResponseError(HttpStatus.NOT_FOUND.value(), errorMessages.getMessages());

		return super.handleExceptionInternal(ex, re, null, HttpStatus.NOT_FOUND, request);
	}

}
