package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.post.Post;

public interface IPostService {

	public Collection<Post> findByPlantRecordId(Long plantRecordId);

	public Optional<Post> create(Post post);

	public Optional<Post> getPostByIdAndPlantRecordId(Long id, Long postId);

	public boolean delete(Long id, Long postId);

	public Optional<Post> createLikeToPost(Long plantRecordId, Long postId,
			Integer userId);

	public Optional<Post> deleteLikeToPost(Long plantRecordId, Long postId,
			Integer userId);
}
