package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.constants.ResponseConstants;
import com.botapeer.domain.model.like.LikeCountPost;
import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.repository.IPostRepository;
import com.botapeer.domain.service.interfaces.ILikeService;
import com.botapeer.domain.service.interfaces.IPostService;
import com.botapeer.exception.DuplicateKeyException;
import com.botapeer.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

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
	public Optional<Post> getPostByIdAndPlantRecordId(Long id, Long postId) {
		return postRepository.getPostByIdAndPlantRecordId(id, postId);
	}

	@Override
	public boolean delete(Long id, Long postId) {
		return postRepository.delete(id, postId);
	}

	@Override
	public Optional<Post> createLikeToPost(Long plantRecordId, Long postId,
			Integer userId) {
		if (likeService.isLikeWithPost(plantRecordId, postId, userId)) {
			throw new DuplicateKeyException(ResponseConstants.DUPLICATE_KEY_LIKE_CODE);
		}
		likeService.createLikeToPost(plantRecordId, postId, userId);
		Optional<Post> post = getPostByIdAndPlantRecordId(plantRecordId, postId);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postId);

		post.get().setLikeCountPost(likeCountPost);

		return post;
	}

	@Override
	public Optional<Post> deleteLikeToPost(Long plantRecordId, Long postId, Integer userId) {

		if (!likeService.isLikeWithPost(plantRecordId, postId, userId)) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_LIKE_CODE);
		}

		likeService.deleteLikeToPost(plantRecordId, postId, userId);

		Optional<Post> post = getPostByIdAndPlantRecordId(plantRecordId, postId);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postId);

		post.get().setLikeCountPost(likeCountPost);

		return post;
	}

}
