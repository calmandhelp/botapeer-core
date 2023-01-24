package com.botapeer.domain.service.interfaces;

import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;

public interface IPlantRecordService {
	public Optional<PlantRecord> findById(int plantId);

	public Optional<PlantRecord> create(PlantRecord plantRecord);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
