package com.botapeer.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	public User() {
	}

	public User(Integer valueOf, UserName userName, String string, Password password2, Integer valueOf2, String string2,
			String string3, String string4) {
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
