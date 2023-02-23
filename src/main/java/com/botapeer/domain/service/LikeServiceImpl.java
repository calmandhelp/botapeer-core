package com.botapeer.domain.service;

import org.springframework.stereotype.Service;

import com.botapeer.domain.repository.ILikeRepository;
import com.botapeer.domain.service.interfaces.ILikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements ILikeService {

	private final ILikeRepository likeRepository;

	@Override
	public boolean createLikeToPost(String plantRecordId, String postId,
			String userId) {
		return likeRepository.createLikeToPost(plantRecordId, postId, userId);
	}

	@Override
	public boolean isLike(String plantRecordId, String postId, String userId) {
		return likeRepository.isLike(plantRecordId, postId, userId);
	}

}
