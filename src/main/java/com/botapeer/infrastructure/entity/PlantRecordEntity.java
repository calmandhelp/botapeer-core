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

	String title;

	Integer alive;

	String endDate;

	LocalDate createdAt;

	LocalDate updatedAt;
}
