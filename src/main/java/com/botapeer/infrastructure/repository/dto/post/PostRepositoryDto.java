package com.botapeer.infrastructure.repository.dto.post;

import java.util.ArrayList;
import java.util.Collection;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;
import com.botapeer.infrastructure.entity.PostEntity;

public class PostRepositoryDto {

	public static Collection<Post> toModel(Collection<PostEntity> entities) {

		Collection<Post> modelList = new ArrayList<>();

		for (PostEntity entity : entities) {
			Post model = new Post();
			model.setId((long) entity.getId());
			Article article = new Article(entity.getArticle());
			model.setArticle(article);
			model.setImage_url(entity.getImage_url());
			String title = entity.getTitle();
			Title t = new Title(title);
			model.setTitle(t);
			model.setStatus(entity.getStatus());
			model.setPlantRecordId(entity.getPlantRecordId());
			model.setCreated_at(entity.getCreated_at());
			model.setUpdated_at(entity.getUpdated_at());

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
