package com.botapeer.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.botapeer.domain.model.label.Label;
import com.botapeer.domain.repository.ILabelRepository;
import com.botapeer.domain.service.interfaces.ILabelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelSeviceImpl implements ILabelService {

	private final ILabelRepository labelRepository;

	@Override
	public Collection<Label> findById(int plantRecordId) {
		return labelRepository.findById(plantRecordId);
	}

	@Override
	public boolean create(Label label) {
		return labelRepository.create(label);
	}

	//	@Override
	//	public Collection<PlantRecordEntity> findByUserId(int userId) {
	//		return this.plantRepository.findByUserId(userId);
	//	}
	//
	//	@Override
	//	public Collection<PlantRecordEntity> findAll() {
	//		return this.plantRepository.findAll();
	//	}
	//
	//	@Override
	//	public boolean update(PlantRecordEntity plant) {
	//		return this.plantRepository.update(plant);
	//	}
	//
	//	@Override
	//	public boolean delete(int plantId) {
	//		return this.plantRepository.delete(plantId);
	//	}

}
