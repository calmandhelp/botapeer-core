package com.botapeer.infrastructure.entity;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class LikeCountPostEntity {
	private Long postId;
	private Long count;
}
