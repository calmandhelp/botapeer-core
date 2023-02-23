package com.botapeer.infrastructure.repository.dto.like;

import java.util.Optional;

import com.botapeer.domain.model.like.LikeCountPost;
import com.botapeer.infrastructure.entity.LikeCountPostEntity;

public class LikeRepositoryDto {

	public static Optional<LikeCountPost> toModel(Optional<LikeCountPostEntity> entity) {

		if (entity.isPresent()) {
			LikeCountPost model = new LikeCountPost();

			model.setCount(entity.get().getCount());
			model.setPostId(entity.get().getPostId());

			return Optional.ofNullable(model);
		}
		return Optional.empty();

	}

}
