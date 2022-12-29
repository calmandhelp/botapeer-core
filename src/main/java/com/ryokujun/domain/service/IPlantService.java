package com.ryokujun.domain.service;

import java.util.Collection;

import com.ryokujun.domain.entity.Plant;

public interface IPlantService {
	public Plant findById(int plantId);

	public Collection<Plant> findAll();

	public boolean update(Plant plant);

	public boolean delete(int plantId);
}
