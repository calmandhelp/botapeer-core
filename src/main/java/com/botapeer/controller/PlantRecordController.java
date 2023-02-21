package com.botapeer.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.usecase.interfaces.IPlantRecordUsecase;

import api.PlantRecordsApi;
import lombok.RequiredArgsConstructor;
import model.CreatePlantRecordRequest;
import model.CreatePostFormData;
import model.PlantRecordResponse;
import model.PostResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlantRecordController implements PlantRecordsApi {

	private final IPlantRecordUsecase plantUsecase;

	@Override
	@GetMapping("/plant_records/{plantRecordId}")
	public ResponseEntity<PlantRecordResponse> getPlantRecordById(@PathVariable String plantRecordId) {
		Optional<PlantRecordResponse> response = plantUsecase.findById(plantRecordId);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/plant_records")
	public ResponseEntity<PlantRecordResponse> createPlantRecord(
			@Validated @RequestBody CreatePlantRecordRequest request) {
		Optional<PlantRecordResponse> response = plantUsecase.create(request);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/plant_records/users/{userId}")
	public ResponseEntity<List<PlantRecordResponse>> getPlantRecordByUserId(@PathVariable String userId) {
		Collection<PlantRecordResponse> response = plantUsecase.findByUserId(userId);
		List<PlantRecordResponse> list = new ArrayList<>(response);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Override
	@PostMapping("/plant_records/{plantRecordId}/posts")
	public ResponseEntity<PostResponse> createPost(@PathVariable String plantRecordId,
			@RequestPart(value = "image", required = true) MultipartFile image,
			@RequestPart(value = "formData", required = false) CreatePostFormData formData) {
		Optional<PostResponse> response = plantUsecase.createPost(plantRecordId, image, formData);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/plant_records/{plantRecordId}/posts/{postId}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable String plantRecordId, @PathVariable String postId) {
		Optional<PostResponse> response = plantUsecase.getPostByIdAndPostId(plantRecordId, postId);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

}
