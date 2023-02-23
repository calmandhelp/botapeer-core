package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.repository.IPostRepository;
import com.botapeer.domain.service.interfaces.ILikeService;
import com.botapeer.domain.service.interfaces.IPostService;
import com.botapeer.exception.DuplicateKeyException;

import lombok.RequiredArgsConstructor;
import model.CreateLikeToPostRequest;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

	private final IPostRepository postRepository;
	private final ILikeService likeService;

	@Override
	public Collection<Post> findByPlantRecordId(Long plantRecordId) {
		return postRepository.findByPlantRecordId(plantRecordId);
	}

	@Override
	public Optional<Post> create(Post post) {
		Long plantRecordId = postRepository.create(post);
		Optional<Post> p = postRepository.findById(plantRecordId);
		return p;
	}

	@Override
	public Optional<Post> getPostByIdAndPlantRecordId(String id, String postId) {
		return postRepository.getPostByIdAndPlantRecordId(id, postId);
	}

	@Override
	public boolean delete(String id, String postId) {
		return postRepository.delete(id, postId);
	}

	@Override
	public Optional<Post> createLikeToPost(String plantRecordId, String postId,
			CreateLikeToPostRequest createLikeToPostRequest) {
		if (likeService.isLike(plantRecordId, postId, createLikeToPostRequest.getUserId())) {
			throw new DuplicateKeyException(ResponseConstants.DUPLICATE_KEY_LIKE_CODE);
		}
		likeService.createLikeToPost(plantRecordId, postId, createLikeToPostRequest.getUserId());
		return getPostByIdAndPlantRecordId(plantRecordId, postId);
	}

}
