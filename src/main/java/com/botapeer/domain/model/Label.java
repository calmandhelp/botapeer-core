package com.botapeer.domain.model;

import lombok.Data;

@Data
public class Label {
	private String name;

	private Integer plantRecordId;

	public Label(String name) {
		this.name = name;
	}
}
