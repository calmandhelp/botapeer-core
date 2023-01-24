package com.botapeer.controller.payload.label;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LabelResponse {
	@NotEmpty
	private String name;

	public LabelResponse(String name) {
		this.name = name;
	}

	public LabelResponse() {

	}
}
