package com.botapeer.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class PlantRecordEntity {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer userId;

	private String title;

	private Long placeId;

	private Integer status;

	private Integer alive;

	private Optional<LocalDateTime> endDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
