package com.botapeer.usecase.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import model.CreatePlantRecordRequest;
import model.CreatePostFormData;
import model.PlantRecordResponse;
import model.PostResponse;

public interface IPlantRecordUsecase {
	public Optional<PlantRecordResponse> findById(String plantRecordId);

	public Optional<PlantRecordResponse> create(CreatePlantRecordRequest request);

	public Collection<PlantRecordResponse> findByUserId(String userId);

	public Optional<PostResponse> createPost(String plantRecordId, MultipartFile image, CreatePostFormData formData);

	public Optional<PostResponse> getPostByIdAndPostId(String id, String postId);

	//	public Optional<PlantRecordResponse> update(Principal principal, CreatePlantRecordRequest user, MultipartFile coverImage,
	//			MultipartFile profileImage,
	//			BindingResult result);
	//
	//	public void delete(String userId);

	//	public boolean create(UserRequest user);

}
