package com.botapeer.infrastructure.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class PlantRecordEntity {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;

	private String title;

	private Long placeId;

	private Integer status;

	private Integer alive;

	private String endDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;
}
