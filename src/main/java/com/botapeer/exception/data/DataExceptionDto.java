package com.botapeer.exception.data;

import java.util.HashMap;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.exception.ErrorMessages;

public class DataExceptionDto {

	private ErrorMessages errorMessages;

	public ErrorMessages NotFoundErrorToResponse(ErrorMessages errorMessages) {
		this.errorMessages = new ErrorMessages();
		HashMap<String, Object> map = new HashMap<>();
		map.put(ResponseConstants.ERRORS_CODE_KEY, ResponseConstants.NOTFOUND_CODE);
		map.put(ResponseConstants.ERRORS_MESSAGE_KEY, ResponseConstants.NOTFOUND_MESSAGE);
		this.errorMessages.getMessages().add(map);
		return this.errorMessages;
	}

}
