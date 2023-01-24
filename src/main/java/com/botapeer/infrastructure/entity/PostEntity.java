package com.botapeer.infrastructure.entity;

import java.sql.Date;

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

	Date created_at;

	Date updated_at;

}
