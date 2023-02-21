package com.botapeer.usecase.dto.post;

import java.util.Optional;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;
import com.botapeer.util.TimeZoneUtils;

import model.PostResponse;

public class PostResponseDto {
	public static Optional<PostResponse> toResponse(Optional<Post> model) {

		PostResponse response = new PostResponse();

		if (model.isPresent()) {
			Post m = model.get();
			response.setId(m.getId());
			Article article = m.getArticle();
			response.setArticle(article.getArticle());
			response.setImageUrl(m.getImageUrl());
			response.setPlantRecordId(m.getPlantRecordId());
			response.setStatus(m.getStatus());
			Title title = m.getTitle();
			response.setTitle(title.getTitle());
			response.setCreatedAt(m.getCreatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
			response.setUpdatedAt(m.getUpdatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
			return Optional.ofNullable(response);
		}
		return null;
	}
}
