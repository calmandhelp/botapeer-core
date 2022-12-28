package com.ryokujun.controller.error;

import java.util.ArrayList;

public class ErrorMessages {

	private ArrayList<String> errors;

	public ErrorMessages(ArrayList<String> errors) {
		this.errors = errors;
	}

	public void add(String error) {
		this.errors.add(error);
	}

}
