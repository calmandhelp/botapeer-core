package com.botapeer.domain.repository;

public interface ILikeRepository {
	boolean createLikeToPost(String plantRecordId, String postId, String userId);

	boolean isLike(String plantRecordId, String postId, String userId);
}
