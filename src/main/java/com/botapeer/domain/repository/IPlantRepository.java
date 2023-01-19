package com.botapeer.domain.repository;

import java.util.Collection;

import com.botapeer.domain.entity.Plant;

public interface IPlantRepository {
	public Plant findById(int plantId);

	public Collection<Plant> findByUserId(int userId);

	public Collection<Plant> findAll();

	public boolean update(Plant plant);

	public boolean delete(int plantId);
}
