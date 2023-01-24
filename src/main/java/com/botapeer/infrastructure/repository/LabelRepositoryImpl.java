package com.botapeer.infrastructure.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.label.Label;
import com.botapeer.domain.repository.ILabelRepository;
import com.botapeer.infrastructure.mapper.LabelMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LabelRepositoryImpl implements ILabelRepository {

	private final LabelMapper labelMapper;

	@Override
	public Collection<Label> findById(int id) {
		return labelMapper.findById(id);
	}

	@Override
	public boolean create(Label label) {
		return labelMapper.create(label);

	}

}
