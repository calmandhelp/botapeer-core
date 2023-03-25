package com.botapeer.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	public User() {
	}

	public User(Integer id, UserName name, String email, Password password, Integer status, String description,
			String profileImage, String coverImage) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.status = status;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	private Integer id;

	@NotBlank
	private UserName name;

	@Email
	@NotBlank
	private String email;

	private Password password;

	private Integer status;

	private String description;

	private String profileImage;

	private String coverImage;

}
