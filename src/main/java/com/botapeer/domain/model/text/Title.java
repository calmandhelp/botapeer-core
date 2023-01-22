package com.botapeer.domain.model.text;

import lombok.Data;

@Data
public class Title {
	private String title;

	public Title(String title) {
		this.title = title;
	}
}
