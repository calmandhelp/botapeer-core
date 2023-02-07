package com.botapeer.controller.payload.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequest {

	@NotBlank
	private String name;

	@Email
	private String email;

	@NotBlank
	private String password;
}
