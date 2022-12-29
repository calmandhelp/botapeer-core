package com.ryokujun.domain.repository;

import java.util.Collection;

import com.ryokujun.domain.entity.Plant;

public interface IPlantRepository {
	public Plant findById(int plantId);

	public Collection<Plant> findAll();

	public boolean update(Plant plant);

	public boolean delete(int plantId);
}
