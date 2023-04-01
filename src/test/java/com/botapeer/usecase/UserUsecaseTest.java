package com.botapeer.usecase;

import java.util.ArrayList;
import java.util.Collection;

import model.UserResponse;

public class UserUsecaseTest {

	void setup() {
		Collection<UserResponse> userResponses = new ArrayList<>();
		UserResponse userResponse = new UserResponse();
		userResponse.setId(1);
		userResponse.setEmail("taro@taro.com");
		userResponse.setDescription("説明1");
		userResponse.setName("taro");
		userResponse.setStatus(2);
		userResponse.setProfileImage("/image/imagePath1");
		userResponse.setCoverImage("/image/imagePath2");
		userResponses.add(userResponse);
	}

}
