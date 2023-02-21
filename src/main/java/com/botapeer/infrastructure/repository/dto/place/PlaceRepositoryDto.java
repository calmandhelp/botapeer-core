package com.botapeer.infrastructure.repository.dto.place;

import java.util.ArrayList;
import java.util.Collection;

import com.botapeer.domain.model.place.Place;
import com.botapeer.infrastructure.entity.PlaceEntity;

public class PlaceRepositoryDto {

	public static Collection<Place> toModel(Collection<PlaceEntity> entities) {

		Collection<Place> modelList = new ArrayList<>();

		for (PlaceEntity entity : entities) {
			Place model = new Place();
			model.setId(entity.getId());
			model.setName(entity.getName());

			modelList.add(model);
		}

		return modelList;
	}

	// [TODO] 作りかけ	
	//	public static Collection<PostEntity> toEntity(Collection<Post> posts) {
	//
	//		Collection<PostEntity> entityList = new ArrayList<>();
	//
	//		for (Post model : posts) {
	//			PostEntity entity = new PostEntity();
	//			model.setId((long) entity.getId());
	//			model.setArticle(entity.getArticle());
	//			model.setImage_url(null);
	//			
	//			
	//			entityList.add(entity);
	//		}
	//
	//		return entityList;
	//	}

}
