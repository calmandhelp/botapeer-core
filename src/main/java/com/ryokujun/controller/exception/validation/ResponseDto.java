package com.ryokujun.controller.exception.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ryokujun.constants.ResponseConstants;
import com.ryokujun.controller.error.ErrorMessages;

public class ResponseDto {

	private List<HashMap<String, String>> responseList;

	public List<HashMap<String, String>> ValidationErrorToResponse(ErrorMessages errorMessages) {
		this.responseList = new ArrayList<>();
		for (int i = 0; i < errorMessages.getMessages().size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put(ResponseConstants.ERRORS_CODE_KEY,
					validationToResponse(errorMessages.getMessages().get(i).get(ResponseConstants.ERRORS_CODE_KEY)));
			map.put(ResponseConstants.ERRORS_MESSAGE_KEY,
					errorMessages.getMessages().get(i).get(ResponseConstants.ERRORS_MESSAGE_KEY));
			this.responseList.add(map);
		}
		return this.responseList;
	}

	public String validationToResponse(String code) {
		switch (code) {
		case ResponseConstants.EMAIL_VALIDATION:
			return ResponseConstants.EMAIL_VALIDATION_CODE;
		case ResponseConstants.NOT_EMPTY_VALIDATION:
			return ResponseConstants.NOT_EMPTY_VALIDATION_CODE;
		default:
			return null;
		}
	}

}
