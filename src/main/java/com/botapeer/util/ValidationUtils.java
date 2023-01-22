package com.botapeer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.controller.exception.validation.ErrorMessages;
import com.botapeer.controller.exception.validation.ValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ValidationUtils {

	private final MessageSource messageSource;

	public void validation(BindingResult result) {
		if (result.hasErrors()) {
			List<HashMap<String, String>> list = new ArrayList<>();

			result.getAllErrors()
					.stream()
					.map(e -> {
						HashMap<String, String> errorsMap = new HashMap<>();
						var defaultMessage = e.getDefaultMessage();

						if (e instanceof FieldError) {
							var fieldError = (FieldError) e;
							System.out.println(e.getCode());
							errorsMap.put(ResponseConstants.ERRORS_CODE_KEY, e.getCode());
							errorsMap.put(ResponseConstants.ERRORS_MESSAGE_KEY,
									messageSource.getMessage(e, Locale.getDefault()));
							list.add(errorsMap);

							return String.format("%s %s", fieldError.getField(),
									messageSource.getMessage(e, Locale.getDefault()));
						} else {
							return defaultMessage;
						}
					})
					.collect(Collectors.toList());

			ErrorMessages errorMessages = new ErrorMessages();
			errorMessages.setMessages(list);
			throw new ValidationException(errorMessages);
		}
	}
}
