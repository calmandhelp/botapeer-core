package com.botapeer.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserName {
	@NotBlank
	String name;

	public UserName(String name) {
		if (name.length() > 15) {
			throw new IllegalArgumentException("ユーザー名は15文字までです。");
		}
		this.name = name;
	}
}
