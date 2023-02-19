package com.botapeer.infrastructure.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PostEntity {

	private Long id;

	private Integer plantRecordId;

	String title;

	String article;

	String image_url;

	Integer status;

	LocalDateTime createdAt;

	LocalDateTime updatedAt;

}
