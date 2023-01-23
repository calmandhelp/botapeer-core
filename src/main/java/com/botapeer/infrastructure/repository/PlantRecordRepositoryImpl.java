package com.botapeer.infrastructure.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.repository.IPlantRepository;
import com.botapeer.infrastructure.entity.PlantRecordEntity;
import com.botapeer.infrastructure.mapper.PlantRecordMapper;
import com.botapeer.infrastructure.repository.dto.plantRecord.PlantRecordRepositoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PlantRecordRepositoryImpl implements IPlantRepository {

	private final PlantRecordMapper plantRecordMapper;

	@Override
	public Optional<PlantRecord> findById(int id) {
		Optional<PlantRecordEntity> recordEntity = this.plantRecordMapper.findById(id);
		Optional<PlantRecord> record = PlantRecordRepositoryDto.toModel(recordEntity);
		return record;
	}

	@Override
	public boolean create(PlantRecord plantRecord) {
		PlantRecordEntity entity = PlantRecordRepositoryDto.toEntity(plantRecord);
		return this.plantRecordMapper.create(entity);
	}

	//	@Override
	//	public Collection<PlantRecordEntity> findByUserId(int userId) {
	//		return this.plantMapper.findByUserId(userId);
	//	}
	//
	//	@Override
	//	public Collection<PlantRecordEntity> findAll() {
	//		return this.plantMapper.findAll();
	//	}
	//
	//	@Override
	//	public boolean update(PlantRecordEntity plant) {
	//		return this.plantMapper.update(plant);
	//	}
	//
	//	@Override
	//	public boolean delete(int plantId) {
	//		return this.plantMapper.delete(plantId);
	//	}

}
