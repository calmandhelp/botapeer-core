package com.botapeer.infrastructure.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PostEntity {

	private Long id;

	private Long plantRecordId;

	String title;

	String article;

	String imageUrl;

	Integer status;

	LocalDateTime createdAt;

	LocalDateTime updatedAt;

}
