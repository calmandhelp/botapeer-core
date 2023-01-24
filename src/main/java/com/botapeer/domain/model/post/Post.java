package com.botapeer.domain.model.post;

import java.util.Date;

import com.botapeer.domain.model.text.Article;
import com.botapeer.domain.model.text.Title;

import lombok.Data;

@Data
public class Post {

	private Long id;

	private Integer plantRecordId;

	Title title;

	Article article;

	String image_url;

	Integer status;

	Date created_at;

	Date updated_at;

}
