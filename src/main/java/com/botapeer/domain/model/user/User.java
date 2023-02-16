package com.botapeer.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {
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
