package com.botapeer.domain.repository;

import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;

public interface IPlantRecordRepository {
	public Optional<PlantRecord> findById(int plantId);

	public Integer create(PlantRecord plantRecord);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
