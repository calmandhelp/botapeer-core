package com.botapeer.usecase.plantRecord;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.botapeer.controller.payload.label.LabelRequest;
import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
import com.botapeer.domain.model.label.Label;
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

		Collection<Label> labels = new ArrayList<>();

		Collection<LabelRequest> requestLabels = request.getLabels();

		for (LabelRequest labelRequest : requestLabels) {
			Label l = new Label(labelRequest.getName());
			labels.add(l);
		}

		plantRecord.setLabels(labels);

		return plantRecord;
	}

}
