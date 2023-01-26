package com.botapeer.usecase.dto.plantRecord;

import org.springframework.stereotype.Component;

import com.botapeer.controller.payload.plantRecord.CreatePlantRecordRequest;
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

		return plantRecord;
	}

}
