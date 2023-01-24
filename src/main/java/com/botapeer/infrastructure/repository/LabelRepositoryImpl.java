package com.botapeer.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.Label;
import com.botapeer.domain.repository.ILabelRepository;
import com.botapeer.infrastructure.mapper.LabelMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LabelRepositoryImpl implements ILabelRepository {

	private final LabelMapper labelMapper;

	//	@Override
	//	public Optional<PlantRecord> findById(int id) {
	//		Optional<PlantRecordEntity> recordEntity = this.plantRecordMapper.findById(id);
	//		Optional<PlantRecord> record = PlantRecordRepositoryDto.toModel(recordEntity);
	//		return record;
	//	}

	@Override
	public boolean create(Label label) {
		return labelMapper.create(label);

	}

}
