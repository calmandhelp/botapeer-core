package com.botapeer.domain.model.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserName {
	@NotBlank
	private String name;

	public UserName(String name) {
		if (name.length() > 15) {
			throw new IllegalArgumentException("ユーザー名は15文字までです。");
		}
		this.name = name;
	}
}
