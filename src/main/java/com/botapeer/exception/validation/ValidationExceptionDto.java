package com.botapeer.exception.validation;

import java.util.HashMap;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.exception.ErrorMessages;

public class ValidationExceptionDto {

	private ErrorMessages errorMessages;

	public ErrorMessages ValidationCodeConverter(ErrorMessages errorMessages) {
		this.errorMessages = new ErrorMessages();
		for (int i = 0; i < errorMessages.getMessages().size(); i++) {
			HashMap<String, Object> map = new HashMap<>();
			map.put(ResponseConstants.ERRORS_CODE_KEY,
					validationToResponse(errorMessages.getMessages().get(i).get(ResponseConstants.ERRORS_CODE_KEY)));
			map.put(ResponseConstants.ERRORS_MESSAGE_KEY,
					errorMessages.getMessages().get(i).get(ResponseConstants.ERRORS_MESSAGE_KEY));
			this.errorMessages.getMessages().add(map);
		}
		return this.errorMessages;
	}

	public int validationToResponse(Object code) {
		if (code instanceof String) {
			switch (code.toString()) {
			case ResponseConstants.EMAIL_VALIDATION:
				return ResponseConstants.EMAIL_VALIDATION_CODE;
			case ResponseConstants.NOT_EMPTY_VALIDATION:
				return ResponseConstants.NOT_EMPTY_VALIDATION_CODE;
			case ResponseConstants.NOT_BLANK_VALIDATION:
				return ResponseConstants.NOT_BLANK_VALIDATION_CODE;
			case ResponseConstants.NOT_NULL_VALIDATION:
				return ResponseConstants.NOT_NULL_VALIDATION_CODE;
			case ResponseConstants.SIZE_VALIDATION:
				return ResponseConstants.SIZE_VALIDATION_CODE;
			default:
			}
		}
		return 0;
	}

}
