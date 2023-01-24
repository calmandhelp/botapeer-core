package com.botapeer.domain.service.interfaces;

import java.util.Collection;

import com.botapeer.domain.model.Label;

public interface ILabelService {
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
