package com.botapeer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
	private int code;

	public NotFoundException(int code) {
		this.code = code;
	}
}
