package com.botapeer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.exception.DuplicateKeyException;
import com.botapeer.exception.NotFoundException;
import com.botapeer.exception.ValidationExceptionDto;

import lombok.RequiredArgsConstructor;
import model.ErrorInner;
import model.ErrorResponse;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		ErrorResponse re = new ErrorResponse();
		List<ErrorInner> errors = new ArrayList<>();
		ValidationExceptionDto dto = new ValidationExceptionDto();
		if (result.hasErrors()) {
			result.getAllErrors()
					.stream()
					.map(e -> {
						ErrorInner errorInner = new ErrorInner();
						var defaultMessage = e.getDefaultMessage();

						if (e instanceof FieldError) {
							var fieldError = (FieldError) e;
							errorInner.setCode(dto.toCode(e.getCode()));
							errorInner.setMessage(messageSource.getMessage(e, Locale.getDefault()));
							errors.add(errorInner);

							return String.format("%s %s", fieldError.getField(),
									messageSource.getMessage(e, Locale.getDefault()));
						} else {
							return defaultMessage;
						}
					})
					.collect(Collectors.toList());
		}

		re.setErrors(errors);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleValidationException(NotFoundException ex, WebRequest request) {
		List<ErrorInner> errors = new ArrayList<>();
		ErrorInner errorInner = new ErrorInner();
		switch (ex.getCode()) {
		case ResponseConstants.NOTFOUND_USER_CODE:
			errorInner.setCode(ResponseConstants.NOTFOUND_USER_CODE);
			errorInner.setMessage(ResponseConstants.NOTFOUND_USER_MESSAGE);
		case ResponseConstants.NOTFOUND_PLANT_RECORD_CODE:
			errorInner.setCode(ResponseConstants.NOTFOUND_PLANT_RECORD_CODE);
			errorInner.setMessage(ResponseConstants.NOTFOUND_PLANT_RECORD_MESSAGE);
		}

		errors.add(errorInner);
		ErrorResponse re = new ErrorResponse();
		re.setErrors(errors);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex, WebRequest request) {
		List<ErrorInner> errors = new ArrayList<>();
		ErrorInner errorInner = new ErrorInner();
		switch (ex.getCode()) {
		case ResponseConstants.DUPLICATE_KEY_LIKE_CODE:
			errorInner.setCode(ResponseConstants.DUPLICATE_KEY_LIKE_CODE);
			errorInner.setMessage(ResponseConstants.DUPLICATE_KEY_LIKE_MESSAGE);
		}

		errors.add(errorInner);
		ErrorResponse re = new ErrorResponse();
		re.setErrors(errors);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleValidationException(IllegalArgumentException ex, WebRequest request) {
		List<ErrorInner> errors = new ArrayList<>();
		ErrorInner errorInner = new ErrorInner();

		errorInner.setMessage(ex.getMessage());

		ErrorResponse re = new ErrorResponse();
		errors.add(errorInner);

		re.setErrors(errors);

		return super.handleExceptionInternal(ex, re, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
