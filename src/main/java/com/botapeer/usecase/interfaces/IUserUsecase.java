package com.botapeer.usecase.interfaces;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.controller.payload.user.UpdateUserRequest;
import com.botapeer.controller.payload.user.UserResponse;

public interface IUserUsecase {
	public Optional<UserResponse> findById(String userId);

	public Collection<UserResponse> findUsers(String name);

	public Optional<UserResponse> update(Principal principal, UpdateUserRequest user, MultipartFile coverImage,
			MultipartFile profileImage,
			BindingResult result);

	public Optional<UserResponse> updatePassword(Principal principal, UpdatePasswordRequest request,
			BindingResult result);

	public void delete(String userId);

	//	public boolean create(UserRequest user);

	public Optional<UserResponse> findByUserNameOrEmail(String usernameOrEmail);

	public Optional<UserResponse> findByEmail(String email);

	public Optional<UserResponse> findByPlantRecordId(String plantRecordId);
}
