package com.botapeer.controller.payload.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateUserRequest {
	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	private String password;

	private Integer status;

	private String description;

	private String profileImage;

	private String coverImage;

}
