package com.botapeer.domain.model.text;

import lombok.Data;

@Data
public class Article {

	private String article;

	public Article(String article) {
		this.article = article;
	}

}
