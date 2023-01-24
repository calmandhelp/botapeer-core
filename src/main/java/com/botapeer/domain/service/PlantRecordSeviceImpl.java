package com.botapeer.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.botapeer.domain.model.Label;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.repository.ILabelRepository;
import com.botapeer.domain.repository.IPlantRecordRepository;
import com.botapeer.domain.service.interfaces.IPlantRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantRecordSeviceImpl implements IPlantRecordService {

	private final IPlantRecordRepository plantRepository;
	private final ILabelRepository labelRepository;

	@Override
	public Optional<PlantRecord> findById(int plantId) {
		return this.plantRepository.findById(plantId);
	}

	@Override
	@Transactional
	public Optional<PlantRecord> create(PlantRecord plantRecord) {

		int plantRecordId = plantRepository.create(plantRecord);

		for (Label label : plantRecord.getLabels()) {
			label.setPlantRecordId(plantRecordId);
			if (!labelRepository.create(label)) {
				throw new Error();
			}
		}

		Optional<PlantRecord> resultRecord = plantRepository.findById(plantRecordId);

		return resultRecord;
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
