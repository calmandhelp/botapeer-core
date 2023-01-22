package com.botapeer.usecase.dto.user;

import com.botapeer.controller.payload.user.UserRequest;
import com.botapeer.domain.model.User;
import com.botapeer.domain.model.UserName;

public class UserRequestDto {
	public static User toModel(UserRequest request) {
		User user = new User();
		UserName userName = new UserName(request.getName());
		user.setName(userName);
		user.setEmail(request.getEmail());
		user.setId(request.getId());
		user.setProfileImage(request.getProfileImage());
		user.setCoverImage(request.getCoverImage());
		user.setStatus(request.getStatus());
		user.setDescription(request.getDescription());
		return user;
	}
}
