package com.botapeer.domain.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@Email
	private String email;

	private String password;

	private Integer status;

	private String description;

	private String profileImage;

	private String coverImage;

	public boolean isPresent() {
		if (Objects.isNull(this)) {
			return false;
		}
		return true;
	}
}
