package com.ryokujun.controller.aop;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ryokujun.controller.error.ResponseError;
import com.ryokujun.controller.exception.validation.ResponseDto;
import com.ryokujun.controller.exception.validation.ValidationException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
		ResponseDto dto = new ResponseDto();
		List<HashMap<String, String>> response = dto.ValidationErrorToResponse(ex.getErrorMessages());
		ResponseError re = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), response);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
