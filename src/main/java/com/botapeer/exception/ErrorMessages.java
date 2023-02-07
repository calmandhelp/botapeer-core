package com.botapeer.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ErrorMessages {

	private List<HashMap<String, String>> messages;

	public ErrorMessages() {
		this.messages = new ArrayList<HashMap<String, String>>();
	}

}
