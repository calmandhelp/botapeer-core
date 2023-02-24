package com.botapeer.infrastructure.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.model.like.LikeCountPost;
import com.botapeer.domain.repository.ILikeRepository;
import com.botapeer.infrastructure.entity.LikeCountPostEntity;
import com.botapeer.infrastructure.mapper.LikeMapper;
import com.botapeer.infrastructure.repository.dto.like.LikeRepositoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LikeRepositoryImpl implements ILikeRepository {

	private final LikeMapper likeMapper;

	@Override
	public boolean createLikeToPost(Long postId, Integer userId) {
		return likeMapper.createLikeToPost(postId, userId);
	}

	@Override
	public boolean deleteLikeToPost(Long postId, Integer userId) {
		return likeMapper.deleteLikeToPost(postId, userId);
	}

	@Override
	public boolean isLikeWithPost(Long postId, Integer userId) {
		return likeMapper.isLikeWithPost(postId, userId);
	}

	@Override
	public Optional<LikeCountPost> countLikeWithPost(Long postId) {
		Optional<LikeCountPostEntity> entity = likeMapper.CountLikeWithPost(postId);
		Optional<LikeCountPost> likeCountPost = LikeRepositoryDto.toModel(entity);
		return likeCountPost;
	}

}
