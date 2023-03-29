package com.botapeer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class ValidateUtils {

	private static final Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

	public static void validateNull(Object o) {
		if (o == null) {
			logger.error("NullPointerException", o);
			throw new NullPointerException();
		}
	}

	public static void validateNullOrEmpty(Object o) {
		if (o == null) {
			logger.error("NullPointerException", o);
			throw new NullPointerException();
		} else if (ObjectUtils.isEmpty(o)) {
			logger.error("IllegalArgumentException", o);
			throw new IllegalArgumentException();
		}
	}

	public static void validateNotEmpty(Object o) {
		if (!ObjectUtils.isEmpty(o)) {
			logger.error("IllegalArgumentException", o);
			throw new IllegalArgumentException();
		}
	}

	public static void validateNotEmpty(Object... objects) {
		for (Object o : objects) {
			if (!ObjectUtils.isEmpty(o)) {
				logger.error("IllegalArgumentException", o);
				throw new IllegalArgumentException();
			}
		}
	}

	public static void validateZeroOrNegative(Long l) {
		if (l <= 0) {
			logger.error("IllegalArgumentException", l);
			throw new IllegalArgumentException();
		}
	}

}
