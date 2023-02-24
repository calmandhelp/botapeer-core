package com.botapeer.infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

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
	public Optional<Post> findById(Long id) {
		Optional<PostEntity> entity = postMapper.findById(id);
		Optional<Post> model = PostRepositoryDto.toModel(entity);
		return model;
	}

	@Override
	public Collection<Post> findByPlantRecordId(Long id) {
		Collection<PostEntity> entity = postMapper.findByPlantRecordId(id);
		Collection<Post> model = PostRepositoryDto.toModel(entity);
		return model;
	}

	@Override
	public Long create(Post post) {
		PostEntity entity = PostRepositoryDto.toEntity(post);
		postMapper.create(entity);

		return entity.getId();
	}

	@Override
	public boolean delete(Long id) {
		return postMapper.delete(id);
	}

}
