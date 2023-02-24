package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.post.Post;

public interface IPostService {

	public Collection<Post> findByPlantRecordId(Long plantRecordId);

	public Optional<Post> create(Post post);

	public Optional<Post> getById(Long id);

	public boolean delete(Long id);

	public Optional<Post> createLikeToPost(Long postId,
			Integer userId);

	public Optional<Post> deleteLikeToPost(Long postId,
			Integer userId);
}
