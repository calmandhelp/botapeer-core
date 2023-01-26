package com.botapeer.domain.model.plantRecord;

import java.time.LocalDate;
import java.util.Collection;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Title;

import lombok.Data;

@Data
public class PlantRecord {
	private Integer id;

	private Integer userId;

	private Title title;

	private Place place;

	private Collection<Post> posts;

	private Integer alive;

	private Integer status;

	private String endDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;
}
