package com.botapeer.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.usecase.interfaces.IPostUsecase;

import api.PostsApi;
import lombok.RequiredArgsConstructor;
import model.CreatePostFormData;
import model.PostResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController implements PostsApi {

	private final IPostUsecase postUsecase;

	@Override
	@PostMapping("posts/plant_records/{plantRecordId}")
	public ResponseEntity<PostResponse> createPost(@PathVariable String plantRecordId,
			@RequestPart(value = "image", required = true) MultipartFile image,
			@RequestPart(value = "formData", required = false) @Valid CreatePostFormData formData) {
		Optional<PostResponse> response = postUsecase.createPost(plantRecordId, image, formData);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/posts/{postId}/plant_records/{plantRecordId}")
	public ResponseEntity<PostResponse> getPostByIdAndPlantRecordId(@PathVariable String plantRecordId,
			@PathVariable String postId) {
		Optional<PostResponse> response = postUsecase.getPostByIdAndPlantRecordId(plantRecordId, postId);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/posts/{postId}/plant_records/{plantRecordId}")
	public ResponseEntity<Void> deletePost(@PathVariable String plantRecordId, @PathVariable String postId) {
		postUsecase.deletePost(plantRecordId, postId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponse> createLikeToPost(String paramString1, String paramString2,
			@Valid String paramString3) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
