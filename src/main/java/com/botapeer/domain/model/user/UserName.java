package com.botapeer.domain.model.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.botapeer.util.ValidateUtils;

import lombok.Data;

@Data
public class UserName {

	@NotNull
	private String name;

	public UserName(String name) {
		Map<String, String> errorMessages = new HashMap<>();
		Optional<String> validationMessage;
		validationMessage = ValidateUtils.validateNullOrEmpty(name, "name is null or empty");
		validationMessage.ifPresent(msg -> errorMessages.put("name_nullOrEmpty", msg));

		if (!errorMessages.isEmpty()) {
			throw new IllegalArgumentException(errorMessages.toString());
		}

		this.name = name;
	}
}
