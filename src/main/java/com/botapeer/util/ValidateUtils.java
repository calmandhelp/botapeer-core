package com.botapeer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class ValidateUtils {

	private static final Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

	public static void validNullOrEmpty(Object o) {
		if (o == null) {
			logger.error("NullPointerException", o);
			throw new NullPointerException();
		} else if (ObjectUtils.isEmpty(o)) {
			logger.error("IllegalArgumentException", o);
			throw new IllegalArgumentException();
		}
	}

}
