package com.botapeer.domain.service;

import java.util.Collection;

import com.botapeer.domain.entity.Plant;

public interface IPlantService {
	public Plant findById(int plantId);

	public Collection<Plant> findByUserId(int userId);

	public Collection<Plant> findAll();

	public boolean update(Plant plant);

	public boolean delete(int plantId);
}
