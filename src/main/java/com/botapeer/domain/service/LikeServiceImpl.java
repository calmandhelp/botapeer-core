package com.botapeer.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.domain.model.like.LikeCountPost;
import com.botapeer.domain.repository.ILikeRepository;
import com.botapeer.domain.service.interfaces.ILikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements ILikeService {

	private final ILikeRepository likeRepository;

	@Override
	public boolean createLikeToPost(Long postId, Integer userId) {
		return likeRepository.createLikeToPost(postId, userId);
	}

	@Override
	public boolean isLikeWithPost(Long postId, Integer userId) {
		return likeRepository.isLikeWithPost(postId, userId);
	}

	@Override
	public Optional<LikeCountPost> countLikeWithPost(Long postId) {
		return likeRepository.countLikeWithPost(postId);
	}

	@Override
	public boolean deleteLikeToPost(Long postId, Integer userId) {
		return likeRepository.deleteLikeToPost(postId, userId);
	}

}
