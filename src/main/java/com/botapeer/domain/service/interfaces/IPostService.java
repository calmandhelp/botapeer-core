package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.post.Post;

public interface IPostService {

	Collection<Post> findByPlantRecordId(Long plantRecordId);

	Optional<Post> create(Post post);

	Optional<Post> getById(Long id);

	boolean delete(Long id);

	Optional<Post> createLikeToPost(Long postId,
			Integer userId);

	Optional<Post> deleteLikeToPost(Long postId,
			Integer userId);
}
