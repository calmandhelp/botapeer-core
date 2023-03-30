package com.botapeer.domain.model.user;

import javax.validation.constraints.NotNull;

import com.botapeer.util.ValidateUtils;

import lombok.Data;

@Data
public class UserName {

	@NotNull
	private String name;

	public UserName(String name) {
		ValidateUtils.validateNullOrEmpty(name);
		this.name = name;
	}
}
