package com.botapeer.controller.payload.plantRecord;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreatePlantRecordRequest {
	@NotBlank
	private String title;

	@NotNull
	private Long placeId;

	public CreatePlantRecordRequest(String title, Long placeId) {
		this.title = title;
		this.placeId = placeId;
	}

	public CreatePlantRecordRequest() {

	}

}
