package com.botapeer.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

	private ErrorMessages errorMessages;

	public ValidationException(ErrorMessages errorMessages) {
		this.errorMessages = errorMessages;
	}
}
