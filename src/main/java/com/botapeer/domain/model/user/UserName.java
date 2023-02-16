package com.botapeer.domain.model.user;

import lombok.Data;

@Data
public class UserName {
	private String name;

	public UserName(String name) {
		this.name = name;
	}
}
