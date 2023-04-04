package com.botapeer.usecase;

import java.io.IOException;
import java.util.Optional;

import com.botapeer.adapter.IUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.domain.model.like.LikeCountPost;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.service.interfaces.ILikeService;
import com.botapeer.domain.service.interfaces.IPostService;
import com.botapeer.domain.service.interfaces.IUserService;
import com.botapeer.usecase.dto.plantRecord.CreatePostFormDataDto;
import com.botapeer.usecase.dto.post.PostResponseDto;
import com.botapeer.usecase.interfaces.IPostUsecase;
import com.botapeer.adapter.Uploader;

import lombok.RequiredArgsConstructor;
import model.CreatePostFormData;
import model.LikeResponse;
import model.PostResponse;

@Component
@RequiredArgsConstructor
public class PostUsecase implements IPostUsecase {

	private final IPostService postService;
	private final ILikeService likeService;
	private final IUserService userService;
	private final IUploader uploader;

	Logger logger = LoggerFactory.getLogger(PlantRecordUsecase.class);

	@Value(value = "${imagePath}")
	private String imagePath;

	@Override
	public Optional<PostResponse> createPost(String plantRecordId, MultipartFile image, CreatePostFormData formData) {

		Long plantRecordIdL = Long.parseLong(plantRecordId);

		String fileName = null;
		try {
			fileName = uploader.uploadImage(image);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Post post = CreatePostFormDataDto.toModel(formData);
		post.setImageUrl(imagePath + fileName);
		post.setStatus(1);
		post.setPlantRecordId(plantRecordIdL);
		Optional<Post> p = postService.create(post);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);
		return response;
	}

	@Override
	public Optional<PostResponse> getById(String postId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();
		Optional<User> user = userService.findByName(userName);

		Long postIdL = Long.parseLong(postId);

		Optional<Post> p = postService.getById(postIdL);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postIdL);

		boolean isLikeWithUser = false;
		if (user != null) {
			isLikeWithUser = likeService.isLikeWithPost(postIdL, user.get().getId());
		}

		response.get().setLike(new LikeResponse());
		if (likeCountPost.isPresent()) {
			response.get().getLike().setCount(likeCountPost.get().getCount());
			response.get().getLike().setIsLikeWithRequestUser(isLikeWithUser);
		} else {
			response.get().getLike().setCount((long) 0);
			response.get().getLike().setIsLikeWithRequestUser(false);
		}

		return response;
	}

	@Override
	public boolean deletePost(String postId) {
		Long postIdL = Long.parseLong(postId);

		return postService.delete(postIdL);
	}

	@Override
	public Optional<PostResponse> createLikeToPost(String postId, String userId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();
		Optional<User> user = userService.findByName(userName);

		Long postIdL = Long.parseLong(postId);
		Integer userIdL = Integer.parseInt(userId);

		Optional<Post> p = postService.createLikeToPost(postIdL, userIdL);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postIdL);

		boolean isLikeWithUser = false;
		if (user != null) {
			isLikeWithUser = likeService.isLikeWithPost(postIdL, user.get().getId());
		}

		response.get().setLike(new LikeResponse());
		if (likeCountPost.isPresent()) {
			response.get().getLike().setCount(likeCountPost.get().getCount());
			response.get().getLike().setIsLikeWithRequestUser(isLikeWithUser);
		} else {
			response.get().getLike().setCount((long) 0);
			response.get().getLike().setIsLikeWithRequestUser(false);
		}

		return response;
	}

	@Override
	public Optional<PostResponse> deleteLikeToPost(String postId, String userId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();
		Optional<User> user = userService.findByName(userName);

		Long postIdL = Long.parseLong(postId);
		Integer userIdL = Integer.parseInt(userId);

		Optional<Post> p = postService.deleteLikeToPost(postIdL, userIdL);
		Optional<PostResponse> response = PostResponseDto.toResponse(p);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postIdL);

		boolean isLikeWithUser = false;
		if (user != null) {
			isLikeWithUser = likeService.isLikeWithPost(postIdL, user.get().getId());
		}

		response.get().setLike(new LikeResponse());
		if (likeCountPost.isPresent()) {
			response.get().getLike().setCount(likeCountPost.get().getCount());
			response.get().getLike().setIsLikeWithRequestUser(isLikeWithUser);
		} else {
			response.get().getLike().setCount((long) 0);
			response.get().getLike().setIsLikeWithRequestUser(false);
		}

		return response;
	}

}
