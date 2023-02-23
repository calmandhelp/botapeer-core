package com.botapeer.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.botapeer.domain.repository.ILikeRepository;
import com.botapeer.infrastructure.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LikeRepositoryImpl implements ILikeRepository {

	private final LikeMapper likeMapper;

	@Override
	public boolean createLikeToPost(String plantRecordId, String postId,
			String userId) {
		return likeMapper.createLikeToPost(plantRecordId, postId, userId);
	}

	@Override
	public boolean isLike(String plantRecordId, String postId, String userId) {
		return likeMapper.isLike(plantRecordId, postId, userId);
	}

}
