package com.botapeer.usecase;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.service.interfaces.IPostService;
import com.botapeer.usecase.dto.plantRecord.CreatePostFormDataDto;
import com.botapeer.usecase.dto.post.PostResponseDto;
import com.botapeer.usecase.interfaces.IPostUsecase;
import com.botapeer.util.ImageUtils;

import lombok.RequiredArgsConstructor;
import model.CreatePostFormData;
import model.PostResponse;

@Component
@RequiredArgsConstructor
public class PostUsecase implements IPostUsecase {

	private final IPostService postService;
	private final ImageUtils imageUtils;

	Logger logger = LoggerFactory.getLogger(PlantRecordUsecase.class);

	@Value(value = "${imagePath}")
	private String imagePath;

	@Override
	public Optional<PostResponse> createPost(String plantRecordId, MultipartFile image, CreatePostFormData formData) {

		Long plantRecordIdL = Long.parseLong(plantRecordId);

		String fileName = imageUtils.uploadImage(image);
		Post post = CreatePostFormDataDto.toModel(formData);
		post.setImageUrl(imagePath + fileName);
		post.setStatus(1);
		post.setPlantRecordId(plantRecordIdL);
		Optional<Post> p = postService.create(post);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);
		return response;
	}

	@Override
	public Optional<PostResponse> getPostByIdAndPlantRecordId(String id, String postId) {
		Optional<Post> p = postService.getPostByIdAndPlantRecordId(id, postId);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);
		return response;
	}

	@Override
	public boolean deletePost(String id, String postId) {
		return postService.delete(id, postId);
	}

}
