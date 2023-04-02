package com.botapeer.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class User {

	public User() {
	}

	public User(Integer id, UserName name, String email, String description,
			String profileImage, String coverImage) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	public User(UserName name, String email, Password password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(UserName name, String email, String description,
			String profileImage, String coverImage) {
		this.name = name;
		this.email = email;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	public User(Integer id, UserName name, String email, Password password, Integer status, String description,
			String profileImage, String coverImage) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.status = status;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	@Positive
	private Integer id;

	@NotNull
	private UserName name;

	@Email
	@NotBlank
	private String email;

	private Password password;

	private Integer status;

	@NotNull
	private String description;

	@NotNull
	private String profileImage;

	@NotNull
	private String coverImage;

}
