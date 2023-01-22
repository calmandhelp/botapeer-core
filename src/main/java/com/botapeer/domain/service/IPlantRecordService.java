package com.botapeer.domain.service;

import java.util.Collection;

import com.botapeer.infrastructure.entity.PlantRecord;

public interface IPlantRecordService {
	public PlantRecord findById(int plantId);

	public Collection<PlantRecord> findByUserId(int userId);

	public Collection<PlantRecord> findAll();

	public boolean update(PlantRecord plant);

	public boolean delete(int plantId);
}
