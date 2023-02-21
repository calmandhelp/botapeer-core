package com.botapeer.infrastructure.repository.dto.post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
			model.setImageUrl(entity.getImageUrl());
			String title = entity.getTitle();
			Title t = new Title(title);
			model.setTitle(t);
			model.setStatus(entity.getStatus());
			model.setPlantRecordId(entity.getPlantRecordId());
			model.setCreatedAt(entity.getCreatedAt());
			model.setUpdatedAt(entity.getUpdatedAt());

			modelList.add(model);
		}

		return modelList;
	}

	public static Optional<Post> toModel(Optional<PostEntity> entity) {
		if (entity.isPresent()) {
			Post model = new Post();
			PostEntity e = entity.get();
			model.setId(e.getId());
			Article article = new Article(e.getArticle());
			model.setArticle(article);
			model.setImageUrl(e.getImageUrl());
			Title title = new Title(e.getTitle());
			model.setTitle(title);
			model.setPlantRecordId(e.getPlantRecordId());
			model.setStatus(e.getStatus());
			model.setCreatedAt(e.getCreatedAt());
			model.setUpdatedAt(e.getCreatedAt());
			return Optional.ofNullable(model);
		}
		return null;
	}

	public static PostEntity toEntity(Post model) {
		PostEntity entity = new PostEntity();
		entity.setImageUrl(model.getImageUrl());
		entity.setPlantRecordId(model.getPlantRecordId());
		entity.setStatus(model.getStatus());
		Title title = model.getTitle();
		entity.setTitle(title.getTitle());
		Article article = model.getArticle();
		entity.setArticle(article.getArticle());
		return entity;
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
