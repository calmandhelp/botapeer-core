package com.botapeer.usecase.interfaces;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import model.CreateUserRequest;
import model.UpdateUserFormData;
import model.UserResponse;

public interface IUserUsecase {
	public Optional<UserResponse> findById(String userId);

	public Collection<UserResponse> findUsers(String name);

	public Optional<UserResponse> create(CreateUserRequest request);

	public Optional<UserResponse> update(UpdateUserFormData user, MultipartFile coverImage,
			MultipartFile profileImage, String userId);

	//	public Optional<User> updatePassword(Principal principal, UpdatePasswordRequest request,
	//			BindingResult result);

	public void delete(String userId);

	public Optional<UserResponse> findByPlantRecordId(String plantRecordId);
}
