package com.botapeer.controller.payload.plantRecord;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlantRecordResponse {
	private Integer id;

	String title;

	Integer alive;

	String endDate;

	LocalDate createdAt;

	LocalDate updatedAt;

}
