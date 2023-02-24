package com.botapeer.usecase.interfaces;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import model.CreatePostFormData;
import model.PostResponse;

public interface IPostUsecase {
	public Optional<PostResponse> createPost(String plantRecordId, MultipartFile image, CreatePostFormData formData);

	public Optional<PostResponse> getById(String postId);

	public boolean deletePost(String postId);

	public Optional<PostResponse> createLikeToPost(String postId, String userId);

	public Optional<PostResponse> deleteLikeToPost(String postId, String userId);
}
