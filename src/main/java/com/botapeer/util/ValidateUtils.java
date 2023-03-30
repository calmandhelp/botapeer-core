package com.botapeer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class ValidateUtils {

	private static final Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

	public static void validateNull(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
	}

	public static void validateNullOrEmpty(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else if (ObjectUtils.isEmpty(o)) {
			throw new IllegalArgumentException();
		}
	}

	public static void validateNullOrEmpty(Object... objects) {
		for (Object o : objects) {
			if (o == null) {
				throw new NullPointerException();
			} else if (ObjectUtils.isEmpty(o)) {
				throw new IllegalArgumentException();
			}
		}
	}

	public static void validateNotEmpty(Object o) {
		if (!ObjectUtils.isEmpty(o)) {
			throw new IllegalArgumentException();
		}
	}

	public static void validatePresent(Object... objects) {
		for (Object o : objects) {
			if (!ObjectUtils.isEmpty(o)) {
				throw new IllegalArgumentException();
			}
		}
	}

	public static void validateZeroOrNegative(Long l) {
		if (l <= 0) {
			throw new IllegalArgumentException();
		}
	}

	public static void validateZeroOrNegative(Integer l) {
		if (l <= 0) {
			throw new IllegalArgumentException();
		}
	}

}
