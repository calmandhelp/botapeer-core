package com.botapeer.controller.payload.user;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserRequest {
	private Integer id;

	private String name;

	@Email
	private String email;

	private String password;

	private Integer status;

	private String description;

	private String profileImage;

	private String coverImage;

}
