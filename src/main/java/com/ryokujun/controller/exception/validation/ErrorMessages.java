package com.ryokujun.controller.exception.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessages {

	private List<HashMap<String, String>> messages;

	public ErrorMessages() {
		messages = new ArrayList<HashMap<String, String>>();
	}

}
