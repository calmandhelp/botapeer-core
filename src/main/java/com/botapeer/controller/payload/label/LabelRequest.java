package com.botapeer.controller.payload.label;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LabelRequest {
	@NotEmpty
	private String name;

	public LabelRequest(String name) {
		this.name = name;
	}

	public LabelRequest() {

	}
}
