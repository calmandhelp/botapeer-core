package com.botapeer.infrastructure.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class PlantRecordUserPlaceEntity {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantRecordid;

	private Integer userId;

	private Integer placeId;

	private String plantRecordTitle;

	private Integer alive;

	private Integer plantRecordStatus;

	private LocalDate endDate;

	private String email;

	private String description;

	private String userName;

	private LocalDate plantRecordCreatedAt;

	private LocalDate plantRecordUpdatedAt;

	private String placeName;
}
