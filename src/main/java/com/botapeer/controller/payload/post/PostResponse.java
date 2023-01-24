package com.botapeer.controller.payload.post;

import java.util.Date;

import lombok.Data;

@Data
public class PostResponse {
	private Long id;

	private Integer plantRecordId;

	String title;

	String article;

	String image_url;

	Integer status;

	Date created_at;

	Date updated_at;
}
