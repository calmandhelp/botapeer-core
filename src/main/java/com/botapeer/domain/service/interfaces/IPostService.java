package com.botapeer.domain.service.interfaces;

import java.util.Collection;

import com.botapeer.domain.model.post.Post;

public interface IPostService {

	public Collection<Post> findByPlantRecordId(Long plantRecordId);

	//	public boolean create(Label label);

	//	public Collection<PlantRecordEntity> findByUserId(int userId);
	//
	//	public Collection<PlantRecordEntity> findAll();
	//
	//	public boolean update(PlantRecordEntity plant);
	//
	//	public boolean delete(int plantId);
}
