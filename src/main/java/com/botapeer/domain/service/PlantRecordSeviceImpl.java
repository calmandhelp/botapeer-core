package com.botapeer.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.botapeer.infrastructure.entity.PlantRecord;
import com.botapeer.domain.repository.IPlantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantRecordSeviceImpl implements IPlantRecordService {

	private final IPlantRepository plantRepository;

	@Override
	public PlantRecord findById(int plantId) {
		return this.plantRepository.findById(plantId);
	}

	@Override
	public Collection<PlantRecord> findByUserId(int userId) {
		return this.plantRepository.findByUserId(userId);
	}

	@Override
	public Collection<PlantRecord> findAll() {
		return this.plantRepository.findAll();
	}

	@Override
	public boolean update(PlantRecord plant) {
		return this.plantRepository.update(plant);
	}

	@Override
	public boolean delete(int plantId) {
		return this.plantRepository.delete(plantId);
	}

}
