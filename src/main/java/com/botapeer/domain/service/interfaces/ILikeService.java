package com.botapeer.domain.service.interfaces;

import java.util.Optional;

import com.botapeer.domain.model.like.LikeCountPost;

public interface ILikeService {

	public boolean createLikeToPost(Long postId,
			Integer userId);

	public boolean deleteLikeToPost(Long postId,
			Integer userId);

	boolean isLikeWithPost(Long postId, Integer userId);

	public Optional<LikeCountPost> countLikeWithPost(Long postId);
}
