package com.botapeer.usecase.plantRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.controller.payload.label.LabelResponse;
import com.botapeer.controller.payload.plantRecord.PlantRecordResponse;
import com.botapeer.controller.payload.post.PostResponse;
import com.botapeer.domain.model.label.Label;
import com.botapeer.domain.model.plantRecord.PlantRecord;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;

public class PlantRecordResponseDto {
	public static Optional<PlantRecordResponse> toResponse(Optional<PlantRecord> model) {
		if (model.isPresent()) {
			PlantRecordResponse response = new PlantRecordResponse();
			response.setId(model.get().getId());
			response.setAlive(model.get().getAlive());
			Title title = model.get().getTitle();
			String t = title.getTitle();
			response.setTitle(t);
			response.setCreatedAt(model.get().getCreatedAt());
			response.setEndDate(model.get().getEndDate());
			response.setUpdatedAt(model.get().getUpdatedAt());

			Collection<LabelResponse> labels = new ArrayList<>();
			for (Label label : model.get().getLabels()) {
				LabelResponse labelResponse = new LabelResponse(label.getName());
				labels.add(labelResponse);
			}
			response.setLabels(labels);

			Collection<PostResponse> posts = new ArrayList<>();
			for (Post postModel : model.get().getPosts()) {
				PostResponse postResponse = new PostResponse();
				postResponse.setId(postModel.getId());
				postResponse.setPlantRecordId(postModel.getPlantRecordId());
				Article article = postModel.getArticle();
				postResponse.setArticle(article.getArticle());
				postResponse.setCreated_at(postModel.getCreated_at());
				postResponse.setUpdated_at(postModel.getUpdated_at());
				postResponse.setImage_url(postModel.getImage_url());
				postResponse.setStatus(postModel.getStatus());
				Title postTitle = postModel.getTitle();
				String PostT = postTitle.getTitle();
				postResponse.setTitle(PostT);
				posts.add(postResponse);
			}
			response.setPosts(posts);

			return Optional.ofNullable(response);
		}
		return null;
	}

	public static Collection<PlantRecordResponse> toResponse(Collection<PlantRecord> models) {

		Collection<PlantRecordResponse> responses = new ArrayList<PlantRecordResponse>();

		for (PlantRecord model : models) {
			PlantRecordResponse r = new PlantRecordResponse();
			r.setId(model.getId());
			r.setAlive(model.getAlive());
			r.setCreatedAt(model.getCreatedAt());
			r.setEndDate(model.getEndDate());
			Title title = model.getTitle();
			r.setTitle(title.getTitle());
			r.setUpdatedAt(model.getUpdatedAt());
			Collection<Label> labels = model.getLabels();
			Collection<LabelResponse> labelResponses = new ArrayList<>();
			for (Label l : labels) {
				LabelResponse labelResponse = new LabelResponse(l.getName());
				labelResponses.add(labelResponse);
			}
			r.setLabels(labelResponses);

			Collection<Post> posts = model.getPosts();
			Collection<PostResponse> postResponses = new ArrayList<>();
			for (Post p : posts) {
				PostResponse postResponse = new PostResponse();
				Article article = p.getArticle();
				String a = article.getArticle();
				postResponse.setId(p.getId());
				postResponse.setArticle(a);
				postResponse.setCreated_at(p.getCreated_at());
				postResponse.setUpdated_at(p.getUpdated_at());
				postResponse.setImage_url(p.getImage_url());
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
