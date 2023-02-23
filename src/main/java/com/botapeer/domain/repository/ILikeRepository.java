package com.botapeer.domain.repository;

import java.util.Optional;

import com.botapeer.domain.model.like.LikeCountPost;

public interface ILikeRepository {
	boolean createLikeToPost(Long plantRecordId, Long postId, Integer userId);

	boolean deleteLikeToPost(Long plantRecordId, Long postId, Integer userId);

	boolean isLikeWithPost(Long plantRecordId, Long postId, Integer userId);

	public Optional<LikeCountPost> countLikeWithPost(Long postId);
}
