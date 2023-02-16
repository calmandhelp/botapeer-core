package com.botapeer.exception.validation;

import com.botapeer.constants.ResponseConstants;

public class ValidationExceptionDto {

	public int toCode(Object code) {
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
