package com.botapeer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateKeyException extends RuntimeException {
	private int code;

	public DuplicateKeyException(int code) {
		this.code = code;
	}
}
