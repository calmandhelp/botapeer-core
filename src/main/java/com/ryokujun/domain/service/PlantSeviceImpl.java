package com.ryokujun.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.ryokujun.domain.entity.Plant;
import com.ryokujun.domain.repository.IPlantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantSeviceImpl implements IPlantService {

	private final IPlantRepository plantRepository;

	@Override
	public Plant findById(int plantId) {
		return this.plantRepository.findById(plantId);
	}

	@Override
	public Collection<Plant> findAll() {
		return this.plantRepository.findAll();
	}

	@Override
	public boolean update(Plant plant) {
		return this.plantRepository.update(plant);
	}

	@Override
	public boolean delete(int plantId) {
		return this.plantRepository.delete(plantId);
	}

}
