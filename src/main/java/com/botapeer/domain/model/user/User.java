package com.botapeer.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

import lombok.Data;

@Data
public class User {

	public User() {
	}

	public User(Integer id, UserName name, String email, String description,
			String profileImage, String coverImage) {
		validateArguments(name, email, description, profileImage, coverImage);
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	public User(UserName name, String email, String description,
			String profileImage, String coverImage) {
		validateArguments(name, email, description, profileImage, coverImage);
		this.name = name;
		this.email = email;
		this.description = description;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	public User(Integer id, UserName name, String email, Password password, Integer status, String description,
			String profileImage, String coverImage) {
		validateArguments(name, email, status, description, profileImage, coverImage);
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
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

	@NotNull
	private Integer status;

	@NotNull
	private String description;

	@NotNull
	private String profileImage;

	@NotNull
	private String coverImage;

	private static void validateArguments(UserName name, String email, String description, String profileImage,
			String coverImage) {
		if (name == null || email == null || description == null || profileImage == null || coverImage == null) {
			throw new NullPointerException();
		}
		if (ObjectUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
	}

	private static void validateArguments(UserName name, String email, Integer status, String description,
			String profileImage,
			String coverImage) {
		if (name == null || email == null || status == null || description == null || profileImage == null
				|| coverImage == null) {
			throw new NullPointerException();
		}
		if (ObjectUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
	}

}
