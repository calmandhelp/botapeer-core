package com.botapeer.usecase.dto.plantRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;
import com.botapeer.util.TimeZoneUtils;

import model.PlaceResponse;
import model.PlantRecordResponse;
import model.PostResponse;

public class PlantRecordResponseDto {
	public static Optional<PlantRecordResponse> toResponse(Optional<PlantRecord> model) {
		if (model.isPresent()) {
			PlantRecordResponse response = new PlantRecordResponse();
			response.setId(model.get().getId());
			response.setAlive(model.get().getAlive());
			Title title = model.get().getTitle();
			String t = title.getTitle();
			response.setTitle(t);

			response.setCreatedAt(model.get().getCreatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
			Optional<LocalDateTime> endDate = model.get().getEndDate();
			if (endDate != null) {
				response.setEndDate(endDate.get().atOffset(TimeZoneUtils.getZoneOffset()));
			}
			response.setUpdatedAt(model.get().getUpdatedAt().atOffset(TimeZoneUtils.getZoneOffset()));

			PlaceResponse placeResponse = new PlaceResponse();
			placeResponse.setId(model.get().getPlace().getId());
			placeResponse.setName(model.get().getPlace().getName());
			response.setPlace(placeResponse);

			List<PostResponse> posts = new ArrayList<>();
			for (Post postModel : model.get().getPosts()) {
				PostResponse postResponse = new PostResponse();
				postResponse.setId(postModel.getId());
				postResponse.setPlantRecordId(postModel.getPlantRecordId());
				Article article = postModel.getArticle();
				postResponse.setArticle(article.getArticle());
				postResponse.setCreatedAt(postModel.getCreatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
				postResponse.setUpdatedAt(postModel.getUpdatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
				postResponse.setImageUrl(postModel.getImageUrl());
				postResponse.setStatus(postModel.getStatus());
				Title postTitle = postModel.getTitle();
				String PostT = postTitle.getTitle();
				postResponse.setTitle(PostT);
				posts.add(postResponse);
			}
			response.setPosts(posts);

			return Optional.ofNullable(response);
		}
		return Optional.empty();
	}

	public static Collection<PlantRecordResponse> toResponse(Collection<PlantRecord> models) {

		Collection<PlantRecordResponse> responses = new ArrayList<PlantRecordResponse>();

		for (PlantRecord model : models) {
			PlantRecordResponse r = new PlantRecordResponse();
			r.setId(model.getId());
			r.setAlive(model.getAlive());
			r.setCreatedAt(model.getCreatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
			if (model.getEndDate() != null) {
				r.setEndDate(model.getEndDate().get().atOffset(TimeZoneUtils.getZoneOffset()));
			}
			Title title = model.getTitle();
			r.setTitle(title.getTitle());
			r.setUpdatedAt(model.getUpdatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
			PlaceResponse placeResponse = new PlaceResponse();
			placeResponse.setId(model.getPlace().getId());
			placeResponse.setName(model.getPlace().getName());
			r.setPlace(placeResponse);

			Collection<Post> posts = model.getPosts();
			List<PostResponse> postResponses = new ArrayList<>();
			for (Post p : posts) {
				PostResponse postResponse = new PostResponse();
				Article article = p.getArticle();
				String a = article.getArticle();
				postResponse.setId(p.getId());
				postResponse.setArticle(a);
				postResponse.setCreatedAt(p.getCreatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
				postResponse.setUpdatedAt(p.getUpdatedAt().atOffset(TimeZoneUtils.getZoneOffset()));
				postResponse.setImageUrl(p.getImageUrl());
				postResponse.setPlantRecordId(p.getPlantRecordId());
				postResponse.setStatus(p.getStatus());
				Title postTitle = p.getTitle();
				String strPostTitle = postTitle.getTitle();
				postResponse.setTitle(strPostTitle);
				postResponses.add(postResponse);
			}

			r.setPosts(postResponses);

			responses.add(r);
		}

		return responses;
	}
}
