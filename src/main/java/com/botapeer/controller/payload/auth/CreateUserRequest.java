package com.botapeer.controller.payload.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequest {

	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public CreateUserRequest(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
