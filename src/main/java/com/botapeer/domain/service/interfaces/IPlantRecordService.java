package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;

public interface IPlantRecordService {
	Optional<PlantRecord> findById(Long plantId);

	Optional<PlantRecord> create(PlantRecord plantRecord);

	Collection<PlantRecord> findByUserId(Long userId);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
