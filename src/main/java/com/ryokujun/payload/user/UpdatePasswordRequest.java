package com.ryokujun.payload.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
	@NotBlank
	private String currentPassword;

	@NotBlank
	private String newPassword;
}
