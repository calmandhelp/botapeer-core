package com.botapeer.usecase.dto.plantRecord;

import org.springframework.stereotype.Component;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;

import lombok.RequiredArgsConstructor;
import model.CreatePostFormData;

@Component
@RequiredArgsConstructor
public class CreatePostFormDataDto {

	public static Post toModel(CreatePostFormData request) {

		Post post = new Post();
		String title = request.getTitle();
		Title t = new Title(title);
		post.setTitle(t);
		String article = request.getArticle();
		Article a = new Article(article);
		post.setArticle(a);

		return post;
	}

}
