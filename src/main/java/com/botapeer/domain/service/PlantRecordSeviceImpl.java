package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.repository.IPlantRecordRepository;
import com.botapeer.domain.service.interfaces.IPlantRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantRecordSeviceImpl implements IPlantRecordService {

	private final IPlantRecordRepository plantRecordRepository;

	@Override
	public Optional<PlantRecord> findById(int plantId) {
		return this.plantRecordRepository.findById(plantId);
	}

	@Override
	@Transactional
	public Optional<PlantRecord> create(PlantRecord plantRecord) {

		int plantRecordId = plantRecordRepository.create(plantRecord);

		Optional<PlantRecord> resultRecord = plantRecordRepository.findById(plantRecordId);

		return resultRecord;
	}

	@Override
	public Collection<PlantRecord> findByUserId(Long userId) {
		return plantRecordRepository.findByUserId(userId);
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
