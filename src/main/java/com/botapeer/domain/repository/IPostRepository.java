package com.botapeer.domain.repository;

import java.util.Collection;

import com.botapeer.domain.model.post.Post;

public interface IPostRepository {

	Collection<Post> findByPlantRecordId(int plantRecordId);
	
}
