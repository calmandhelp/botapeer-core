package com.botapeer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.usecase.interfaces.IUserUsecase;

import api.UsersApi;
import lombok.RequiredArgsConstructor;
import model.UpdateUserFormData;
import model.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController implements UsersApi {

	private final IUserUsecase userUsecase;

	@Override
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getUsersOrGetUserByName(@RequestParam(required = false) String username) {
		Collection<UserResponse> u = userUsecase.findUsers(username);
		List<UserResponse> userList = new ArrayList<>(u);
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@Override
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable String userId) {
		Optional<UserResponse> u = userUsecase.findById(userId);
		return new ResponseEntity<>(u.get(), HttpStatus.OK);
	}

	@Override
	@PatchMapping("/users/{userId}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") String userId,
			@RequestPart(value = "formData", required = false) @Valid UpdateUserFormData formData,
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {
		Optional<UserResponse> u = userUsecase.update(formData, profileImage, coverImage, userId);
		return new ResponseEntity<>(u.get(), HttpStatus.OK);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		userUsecase.delete(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@GetMapping("/users/plant_records/{plantRecordId}")
	public ResponseEntity<UserResponse> findUserByPlantRecordId(@PathVariable String plantRecordId) {
		Optional<UserResponse> user = userUsecase.findByPlantRecordId(plantRecordId);
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}
}
