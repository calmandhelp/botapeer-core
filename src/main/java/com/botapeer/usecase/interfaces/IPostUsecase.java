package com.botapeer.usecase.interfaces;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import model.CreatePostFormData;
import model.PostResponse;

public interface IPostUsecase {
	public Optional<PostResponse> createPost(String plantRecordId, MultipartFile image, CreatePostFormData formData);

	public Optional<PostResponse> getPostByIdAndPlantRecordId(String id, String postId);

	public boolean deletePost(String id, String postId);

}
