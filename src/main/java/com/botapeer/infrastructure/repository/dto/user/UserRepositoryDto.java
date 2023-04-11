package com.botapeer.infrastructure.repository.dto.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.infrastructure.entity.UserEntity;

public class UserRepositoryDto {
	public static Optional<User> toModel(Optional<UserEntity> user) {
		if (user.isPresent()) {
			User u = new User();
			u.setId(user.get().getId());
			u.setEmail(user.get().getEmail());

			String strUserName = user.get().getName();
			UserName userName = new UserName(strUserName);
			u.setName(userName);

			u.setProfileImage(user.get().getProfileImage());
			u.setCoverImage(user.get().getCoverImage());
			u.setStatus(user.get().getStatus());
			u.setDescription(user.get().getDescription());
			String password = user.get().getPassword();
			Password p = new Password(password);
			u.setPassword(p);
			return Optional.ofNullable(u);
		}
		return Optional.empty();
	}

	public static Collection<User> toModel(Collection<UserEntity> users) {

		List<UserEntity> userList = new ArrayList<>(users);

		Collection<User> user = new ArrayList<User>();

		for (UserEntity userEntity : userList) {
			User u = new User();
			u.setId(userEntity.getId());
			u.setEmail(userEntity.getEmail());

			String strUserName = userEntity.getName();
			UserName userName = new UserName(strUserName);
			u.setName(userName);

			u.setProfileImage(userEntity.getProfileImage());
			u.setCoverImage(userEntity.getCoverImage());
			u.setStatus(userEntity.getStatus());
			u.setDescription(userEntity.getDescription());

			user.add(u);
		}

		return user;
	}

	public static UserEntity toEntity(User user) {
		UserEntity u = new UserEntity();
		u.setId(user.getId());
		u.setEmail(user.getEmail());

		UserName userName = user.getName();
		String strUserName = userName.getName();
		u.setName(strUserName);

		u.setProfileImage(user.getProfileImage());
		u.setCoverImage(user.getCoverImage());
		u.setStatus(user.getStatus());
		u.setDescription(user.getDescription());
		return u;
	}
}
