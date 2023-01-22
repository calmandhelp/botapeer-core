package com.botapeer.infrastructure.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.botapeer.infrastructure.entity.PlantRecord;
import com.botapeer.domain.repository.IPlantRepository;
import com.botapeer.infrastructure.mapper.PlantRecordMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PlantRecordRepositoryImpl implements IPlantRepository {

	private final PlantRecordMapper plantMapper;

	@Override
	public PlantRecord findById(int id) {
		return this.plantMapper.findById(id);
	}

	@Override
	public Collection<PlantRecord> findByUserId(int userId) {
		return this.plantMapper.findByUserId(userId);
	}

	@Override
	public Collection<PlantRecord> findAll() {
		return this.plantMapper.findAll();
	}

	@Override
	public boolean update(PlantRecord plant) {
		return this.plantMapper.update(plant);
	}

	@Override
	public boolean delete(int plantId) {
		return this.plantMapper.delete(plantId);
	}

}
