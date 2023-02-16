package com.botapeer.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.controller.payload.user.UpdatePasswordRequest;
import com.botapeer.usecase.interfaces.IUserUsecase;

import api.UsersApi;
import lombok.RequiredArgsConstructor;
import model.UpdateUserFormData;
import model.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController implements UsersApi {

	private final IUserUsecase userUsecase;

	@GetMapping("/users")
	@Override
	public ResponseEntity<List<User>> getUsersOrGetUserByName(@RequestParam(required = false) String username) {
		Collection<User> u = userUsecase.findUsers(username);
		List<User> userList = new ArrayList<>(u);
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	@Override
	public ResponseEntity<User> findUserById(@PathVariable String userId) {
		Optional<User> u = userUsecase.findById(userId);
		return new ResponseEntity<>(u.get(), HttpStatus.OK);
	}

	//	@PostMapping("/users/{userId}")
	//	@Override
	//	public ResponseEntity<User> updateUser(Principal principal,
	//			@RequestPart("formData") @Validated UpdateUserRequest user,
	//			BindingResult result,
	//			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
	//			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {
	//		Optional<User> r = userUsecase.update(principal, user, profileImage, coverImage, result);
	//
	//		return r;
	//	}

	@PostMapping("/users/{userId}")
	@Override
	public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @Valid UpdateUserFormData user,
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {

		Optional<User> u = userUsecase.update(user, profileImage, coverImage);

		return new ResponseEntity<>(u.get(), HttpStatus.OK);
	}

	@PostMapping("/users/{userId}/password")
	public Optional<User> updatePassword(Principal principal,
			@RequestBody @Validated UpdatePasswordRequest request,
			BindingResult result) {
		Optional<User> r = userUsecase.updatePassword(principal, request, result);
		return r;
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable String userId) {
		userUsecase.delete(userId);
		return null;
	}

	@GetMapping("/users/findByEmail")
	public Optional<User> findByEmail(@RequestParam String email) {
		Optional<User> r = userUsecase.findByEmail(email);
		return r;
	}

	@GetMapping("/users/plant_records/{plantRecordId}")
	public Optional<User> findByPlantRecordId(@PathVariable String plantRecordId) {
		Optional<User> response = userUsecase.findByPlantRecordId(plantRecordId);
		return response;
	}

}
