package com.botapeer.domain.model.plantRecord;

import java.time.LocalDate;

import com.botapeer.domain.model.text.Title;

import lombok.Data;

@Data
public class PlantRecord {
	private Integer id;

	private Integer userId;

	Title title;

	Integer alive;

	String endDate;

	LocalDate createdAt;

	LocalDate updatedAt;
}
