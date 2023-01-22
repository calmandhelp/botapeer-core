package com.botapeer.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class PlantRecord {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	int categoryId;

	int userId;

	String title;

	String description;

	String imageUrl;

	String status;

	Integer alive;
}
