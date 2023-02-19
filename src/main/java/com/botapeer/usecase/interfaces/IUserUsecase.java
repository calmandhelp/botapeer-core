package com.botapeer.usecase.interfaces;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;

import model.CreateUserRequest;
import model.UpdateUserFormData;
import model.User;

public interface IUserUsecase {
	public Optional<User> findById(String userId);

	public Collection<User> findUsers(String name);

	public Optional<User> create(CreateUserRequest request, BindingResult result);

	public Optional<User> update(UpdateUserFormData user, MultipartFile coverImage,
			MultipartFile profileImage);

	public Optional<User> updatePassword(Principal principal, UpdatePasswordRequest request,
			BindingResult result);

	public void delete(String userId);

	public Optional<User> findByPlantRecordId(String plantRecordId);
}
