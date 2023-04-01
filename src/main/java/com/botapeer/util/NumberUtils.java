package com.botapeer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtils {

	static Logger logger = LoggerFactory.getLogger(NumberUtils.class);

	public static boolean canString2Number(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			logger.error("NumberFormatException: ", e.getMessage());
			return false;
		}
		return true;
	}

}
