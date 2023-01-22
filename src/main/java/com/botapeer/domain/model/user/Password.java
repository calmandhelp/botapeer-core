package com.botapeer.domain.model.user;

import lombok.Data;

@Data
public class Password {
	String password;

	public Password(String password) {
		if (password.length() < 8) {
			throw new IllegalArgumentException("パスワードは8文字以上です。");
		}
		if (password.length() < 8) {
			throw new IllegalArgumentException("パスワードは20文字以下です。");
		}
		this.password = password;
	}
}
