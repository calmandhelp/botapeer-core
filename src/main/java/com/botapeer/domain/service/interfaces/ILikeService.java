package com.botapeer.domain.service.interfaces;

public interface ILikeService {

	public boolean createLikeToPost(String plantRecordId, String postId,
			String userId);

	boolean isLike(String plantRecordId, String postId, String userId);
}
