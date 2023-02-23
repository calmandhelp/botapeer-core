package com.botapeer.domain.repository;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.post.Post;

public interface IPostRepository {

	Optional<Post> findById(Long id);

	Collection<Post> findByPlantRecordId(Long plantRecordId);

	Long create(Post post);

	Optional<Post> getPostByIdAndPlantRecordId(String plantRecordId, String postId);

	public boolean delete(String plantRecordId, String postId);

}
