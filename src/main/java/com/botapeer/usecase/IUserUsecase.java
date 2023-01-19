package com.botapeer.usecase;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.domain.entity.User;
import com.botapeer.payload.user.UpdatePasswordRequest;

public interface IUserUsecase {
	public Optional<User> findById(Long userId);

	public Collection<User> findUsers();

	public Optional<User> update(Principal principal, User user, MultipartFile coverImage, MultipartFile profileImage,
			BindingResult result);

	public Optional<User> updatePassword(UpdatePasswordRequest request, BindingResult result);

	public boolean delete(Long userId);

	public boolean create(User user);

	public Optional<User> findByUserNameOrEmail(String usernameOrEmail);

	public Optional<User> findByEmail(String email);
}
