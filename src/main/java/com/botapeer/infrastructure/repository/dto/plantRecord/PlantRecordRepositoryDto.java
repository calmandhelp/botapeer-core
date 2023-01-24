package com.botapeer.infrastructure.repository.dto.plantRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.text.Title;
import com.botapeer.infrastructure.entity.PlantRecordEntity;

public class PlantRecordRepositoryDto {
	public static Optional<PlantRecord> toModel(Optional<PlantRecordEntity> entity) {
		if (entity.isPresent()) {
			PlantRecord model = new PlantRecord();
			model.setId(entity.get().getId());
			String title = entity.get().getTitle();
			Title t = new Title(title);
			model.setTitle(t);
			model.setAlive(entity.get().getAlive());
			model.setStatus(entity.get().getStatus());
			model.setCreatedAt(entity.get().getCreatedAt());
			model.setUpdatedAt(entity.get().getUpdatedAt());
			model.setEndDate(entity.get().getEndDate());
			return Optional.ofNullable(model);
		}
		return null;
	}

	public static Collection<PlantRecord> toModel(Collection<PlantRecordEntity> entities) {

		Collection<PlantRecord> plantRecordList = new ArrayList<>();

		for (PlantRecordEntity entity : entities) {
			PlantRecord model = new PlantRecord();
			model.setId(entity.getId());
			model.setAlive(entity.getAlive());
			model.setStatus(entity.getStatus());
			String strTitle = entity.getTitle();
			Title t = new Title(strTitle);
			model.setTitle(t);
			model.setCreatedAt(entity.getCreatedAt());
			model.setUpdatedAt(entity.getUpdatedAt());
			model.setEndDate(entity.getEndDate());
			model.setUserId(entity.getUserId());
			model.setLabels(entity.getLabels());

			plantRecordList.add(model);
		}

		return plantRecordList;
	}

	public static PlantRecordEntity toEntity(PlantRecord plantRecord) {
		PlantRecordEntity plantRecordEntity = new PlantRecordEntity();
		plantRecordEntity.setUserId(plantRecord.getUserId());
		plantRecordEntity.setAlive(plantRecord.getAlive());
		plantRecordEntity.setStatus(plantRecord.getStatus());
		Title title = plantRecord.getTitle();
		String t = title.getTitle();
		plantRecordEntity.setTitle(t);
		plantRecordEntity.setLabels(plantRecord.getLabels());
		return plantRecordEntity;
	}
}
