package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.post.Post;

public interface IPostService {

	public Collection<Post> findByPlantRecordId(Long plantRecordId);

	public Optional<Post> create(Post post);

	public Optional<Post> getPostByIdAndPostId(String id, String postId);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
