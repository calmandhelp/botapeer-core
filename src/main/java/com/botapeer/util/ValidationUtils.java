package com.botapeer.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ValidationUtils {

	private final MessageSource messageSource;

	//	public void validation(BindingResult result) {
	//		if (result.hasErrors()) {
	//			List<HashMap<String, Object>> list = new ArrayList<>();
	//
	//			result.getAllErrors()
	//					.stream()
	//					.map(e -> {
	//						HashMap<String, Object> errorsMap = new HashMap<>();
	//						var defaultMessage = e.getDefaultMessage();
	//
	//						if (e instanceof FieldError) {
	//							var fieldError = (FieldError) e;
	//							errorsMap.put(ResponseConstants.ERRORS_CODE_KEY, e.getCode());
	//							errorsMap.put(ResponseConstants.ERRORS_MESSAGE_KEY,
	//									messageSource.getMessage(e, Locale.getDefault()));
	//							list.add(errorsMap);
	//
	//							return String.format("%s %s", fieldError.getField(),
	//									messageSource.getMessage(e, Locale.getDefault()));
	//						} else {
	//							return defaultMessage;
	//						}
	//					})
	//					.collect(Collectors.toList());
	//
	//			ErrorMessages errorMessages = new ErrorMessages();
	//			errorMessages.setMessages(list);
	//			throw new ValidationException(errorMessages);
	//		}
	//	}
}
