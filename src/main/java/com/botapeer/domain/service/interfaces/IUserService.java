package com.botapeer.domain.service.interfaces;

import java.util.Collection;
import java.util.Optional;

import com.botapeer.domain.model.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

	Optional<User> findById(Long userId);

	Collection<User> findUsers(String username);

	Integer create(User user, String encryptedPassword);

	Optional<User> update(User user, MultipartFile profileImage, MultipartFile coverImage);

	//	boolean updatePassword(UpdatePasswordRequest request);

	boolean delete(Long userId);

	Optional<User> findByUserNameOrEmail(String usernameOrEmail);

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);
}
