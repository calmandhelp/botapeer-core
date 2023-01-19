package com.botapeer.infrastructure.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.entity.Plant;
import com.botapeer.domain.repository.IPlantRepository;
import com.botapeer.infrastructure.mapper.PlantMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PlantRepositoryImpl implements IPlantRepository {

	private final PlantMapper plantMapper;

	@Override
	public Plant findById(int id) {
		return this.plantMapper.findById(id);
	}

	@Override
	public Collection<Plant> findByUserId(int userId) {
		return this.plantMapper.findByUserId(userId);
	}

	@Override
	public Collection<Plant> findAll() {
		return this.plantMapper.findAll();
	}

	@Override
	public boolean update(Plant plant) {
		return this.plantMapper.update(plant);
	}

	@Override
	public boolean delete(int plantId) {
		return this.plantMapper.delete(plantId);
	}

}
