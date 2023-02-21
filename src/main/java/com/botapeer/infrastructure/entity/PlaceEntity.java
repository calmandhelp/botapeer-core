package com.botapeer.infrastructure.entity;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PlaceEntity {
	private Long id;
	private String name;
}
