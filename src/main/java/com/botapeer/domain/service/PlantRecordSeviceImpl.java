package com.botapeer.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.repository.IPlantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantRecordSeviceImpl implements IPlantRecordService {

	private final IPlantRepository plantRepository;

	@Override
	public Optional<PlantRecord> findById(int plantId) {
		return this.plantRepository.findById(plantId);
	}

	@Override
	@Transactional
	public Optional<PlantRecord> create(PlantRecord plantRecord) {

		System.out.println(this.plantRepository.create(plantRecord));
		return null;
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
