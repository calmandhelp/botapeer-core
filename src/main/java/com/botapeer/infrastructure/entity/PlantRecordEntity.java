package com.botapeer.infrastructure.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.botapeer.domain.model.Label;

import lombok.Data;

@Data
@Entity
public class PlantRecordEntity {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;

	private String title;

	private Collection<Label> labels;

	private Integer status;

	private Integer alive;

	private String endDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;
}
