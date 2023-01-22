package com.botapeer.usecase.dto.user;

import org.springframework.stereotype.Component;

import com.botapeer.controller.payload.user.UpdateUserRequest;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateUserRequestDto {

	public static User toModel(UpdateUserRequest request) {
		User user = new User();
		UserName userName = new UserName(request.getName());
		user.setName(userName);
		user.setEmail(request.getEmail());
		user.setProfileImage(request.getProfileImage());
		user.setCoverImage(request.getCoverImage());
		user.setStatus(request.getStatus());
		user.setDescription(request.getDescription());
		return user;
	}

}
