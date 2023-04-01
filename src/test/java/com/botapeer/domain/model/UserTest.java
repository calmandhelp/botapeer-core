package com.botapeer.domain.model;

import org.junit.jupiter.api.Assertions;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;

public class UserTest {

	void testConstructor() {
		User user1 = new User(1, new UserName("taro"), "taro@taro.com", "説明1", "/image/imagePath1",
				"/image/imagePath2");
		Assertions.assertEquals(1, user1.getId());
		Assertions.assertEquals("taro", user1.getName().getName());
		Assertions.assertEquals("taro@taro.com", user1.getEmail());
		Assertions.assertEquals("説明1", user1.getDescription());
		Assertions.assertEquals("/image/imagePath1", user1.getProfileImage());
		Assertions.assertEquals("/image/imagePath2", user1.getCoverImage());

		User user2 = new User(new UserName("taro"), "taro@taro.com", "説明1", "/image/imagePath1",
				"/image/imagePath2");
		Assertions.assertEquals(1, user2.getId());
		Assertions.assertEquals("taro", user2.getName().getName());
		Assertions.assertEquals("taro@taro.com", user2.getEmail());
		Assertions.assertEquals("説明1", user2.getDescription());
		Assertions.assertEquals("/image/imagePath1", user2.getProfileImage());
		Assertions.assertEquals("/image/imagePath2", user2.getCoverImage());

		User user3 = new User(1, new UserName("taro"), "taro@taro.com", new Password("password"), 2, "説明1",
				"/image/imagePath1",
				"/image/imagePath2");
		Assertions.assertEquals(1, user3.getId());
		Assertions.assertEquals("taro", user3.getName().getName());
		Assertions.assertEquals("taro@taro.com", user3.getEmail());
		Assertions.assertEquals("説明1", user3.getDescription());
		Assertions.assertEquals("/image/imagePath1", user3.getProfileImage());
		Assertions.assertEquals("/image/imagePath2", user3.getCoverImage());
	}

}
