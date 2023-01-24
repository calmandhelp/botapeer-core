package com.botapeer.domain.repository;

import java.util.Collection;

import com.botapeer.domain.model.Label;

public interface ILabelRepository {
	public Collection<Label> findById(int plantRecordId);

	public boolean create(Label label);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
