package com.botapeer.domain.model.plantRecord;

import java.time.LocalDate;
import java.util.Collection;

import com.botapeer.domain.model.Label;
import com.botapeer.domain.model.text.Title;

import lombok.Data;

@Data
public class PlantRecord {
	private Integer id;

	private Integer userId;

	private Title title;

	private Collection<Label> labels;

	private Integer alive;

	private Integer status;

	private String endDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;
}
