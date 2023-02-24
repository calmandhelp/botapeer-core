package com.botapeer.domain.repository;

import java.util.Optional;

import com.botapeer.domain.model.like.LikeCountPost;

public interface ILikeRepository {
	boolean createLikeToPost(Long postId, Integer userId);

	boolean deleteLikeToPost(Long postId, Integer userId);

	boolean isLikeWithPost(Long postId, Integer userId);

	public Optional<LikeCountPost> countLikeWithPost(Long postId);
}
