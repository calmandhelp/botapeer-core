package com.botapeer.usecase.dto.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.botapeer.controller.payload.user.UserResponse;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;

public class UserResponseDto {
	public static Optional<UserResponse> toResponse(Optional<User> user) {
		if (user.isPresent()) {
			UserResponse response = new UserResponse();
			UserName userName = user.get().getName();
			response.setName(userName.getName());
			response.setEmail(user.get().getEmail());
			response.setId(user.get().getId());
			response.setProfileImage(user.get().getProfileImage());
			response.setCoverImage(user.get().getCoverImage());
			response.setStatus(user.get().getStatus());
			response.setDescription(user.get().getDescription());
			return Optional.ofNullable(response);
		}
		return null;
	}

	public static Collection<UserResponse> toResponse(Collection<User> users) {

		Collection<UserResponse> responses = new ArrayList<UserResponse>();

		for (User user : users) {
			UserResponse r = new UserResponse();
			r.setId(user.getId());
			r.setEmail(user.getEmail());

			UserName userName = user.getName();
			String strUserName = userName.getName();
			r.setName(strUserName);

			r.setCoverImage(user.getCoverImage());
			r.setProfileImage(user.getProfileImage());
			r.setStatus(user.getStatus());
			r.setDescription(user.getDescription());

			responses.add(r);
		}

		return responses;
	}
}
