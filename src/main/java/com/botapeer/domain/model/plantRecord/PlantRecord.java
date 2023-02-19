package com.botapeer.domain.model.plantRecord;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.place.Place;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.text.Title;

import lombok.Data;

@Data
public class PlantRecord {
	private Long id;

	private Integer userId;

	private Title title;

	private Place place;

	private Collection<Post> posts;

	private Integer alive;

	private Integer status;

	private Optional<LocalDateTime> endDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
