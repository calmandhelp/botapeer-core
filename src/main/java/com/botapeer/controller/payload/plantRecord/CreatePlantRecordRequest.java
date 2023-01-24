package com.botapeer.controller.payload.plantRecord;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.botapeer.controller.payload.label.LabelRequest;

import lombok.Data;

@Data
public class CreatePlantRecordRequest {
	@NotBlank
	private String title;

	@Size(min = 1, max = 20)
	private Collection<LabelRequest> labels;

	public CreatePlantRecordRequest(String title, Collection<LabelRequest> labels) {
		this.title = title;
		this.labels = labels;
	}

	public CreatePlantRecordRequest() {

	}

}
