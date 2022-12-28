package com.ryokujun.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class errorUtil {
	public static String addAllErrors(BindingResult result) {
		String errorMessages = "";
		for (ObjectError error : result.getAllErrors()) {
			// ここでメッセージを取得する。
			errorMessages += error.getDefaultMessage();
		}
		return errorMessages;
	}
}
