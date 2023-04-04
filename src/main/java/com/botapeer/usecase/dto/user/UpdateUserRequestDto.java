package com.botapeer.usecase.dto.user;

import org.springframework.stereotype.Component;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;

import lombok.RequiredArgsConstructor;
import model.CreateUserRequest;
import model.UpdateUserFormData;

@Component
@RequiredArgsConstructor
public class UpdateUserRequestDto {

	public static User toModel(CreateUserRequest request) {
		User user = new User();
		UserName userName = new UserName(request.getName());
		Password password = new Password(request.getPassword());
		user.setPassword(password);
		user.setName(userName);
		user.setEmail(request.getEmail());
		return user;
	}

	public static User toModel(UpdateUserFormData request) {
		User user = new User();
		System.out.println("name: " + request.getName());
		UserName userName = new UserName(request.getName());
		user.setName(userName);
		user.setEmail(request.getEmail());
		user.setDescription(request.getDescription());
		return user;
	}

}
