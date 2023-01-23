package com.botapeer.usecase.plantRecord;

import org.springframework.stereotype.Component;

import com.botapeer.controller.payload.label.LabelRequest;
import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
import com.botapeer.domain.model.Label;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.text.Title;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePlantRecordRequestDto {

	public static PlantRecord toModel(CreatePlantRecordRequest request) {

		PlantRecord plantRecord = new PlantRecord();
		String title = request.getTitle();
		Title t = new Title(title);
		plantRecord.setTitle(t);

		Label[] labels = new Label[request.getLabels().length];

		for (int i = 0; i < request.getLabels().length; i++) {
			LabelRequest[] requestLabels = request.getLabels();
			Label l = new Label(requestLabels[i].getName());
			labels[i] = l;
		}

		plantRecord.setLabels(labels);

		return plantRecord;
	}

}
