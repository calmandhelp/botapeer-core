package com.botapeer.util;

import java.util.Optional;

import org.springframework.util.ObjectUtils;

public class ValidateUtils {

	public static Optional<String> validateNull(Object o, String message) {
		if (o == null) {
			return Optional.of(message);
		}
		return Optional.empty();
	}

	public static Optional<String> validateNullOrEmpty(Object o, String message) {
		if (o == null) {
			return Optional.of(message);
		} else if (ObjectUtils.isEmpty(o)) {
			return Optional.of(message);
		}
		return Optional.empty();
	}

	public static Optional<String> validatePresent(Object o, String message) {
		if (!ObjectUtils.isEmpty(o)) {
			return Optional.of(message);
		}
		return Optional.empty();
	}

	public static Optional<String> validateZeroOrNegative(Long l, String message) {
		if (l <= 0) {
			return Optional.of(message);
		}
		return Optional.empty();
	}

	public static Optional<String> validateZeroOrNegative(Integer l, String message) {
		if (l <= 0) {
			return Optional.of(message);
		}
		return Optional.empty();
	}

}
