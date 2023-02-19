package com.botapeer.infrastructure.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.repository.IPostRepository;
import com.botapeer.infrastructure.entity.PostEntity;
import com.botapeer.infrastructure.mapper.PostMapper;
import com.botapeer.infrastructure.repository.dto.post.PostRepositoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements IPostRepository {

	private final PostMapper postMapper;

	@Override
	public Collection<Post> findByPlantRecordId(Long id) {
		Collection<PostEntity> entity = postMapper.findByPlantRecordId(id);
		Collection<Post> record = PostRepositoryDto.toModel(entity);
		return record;
	}

}
