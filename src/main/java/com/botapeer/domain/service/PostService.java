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
		Long postId = postRepository.create(post);
		Optional<Post> p = getById(postId);
		return p;
	}

	@Override
	public Optional<Post> getById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public boolean delete(Long id) {
		return postRepository.delete(id);
	}

	@Override
	public Optional<Post> createLikeToPost(Long postId,
			Integer userId) {
		if (likeService.isLikeWithPost(postId, userId)) {
			throw new DuplicateKeyException(ResponseConstants.DUPLICATE_KEY_LIKE_CODE);
		}
		likeService.createLikeToPost(postId, userId);
		Optional<Post> post = getById(postId);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postId);

		post.get().setLikeCountPost(likeCountPost);

		return post;
	}

	@Override
	public Optional<Post> deleteLikeToPost(Long postId, Integer userId) {

		if (!likeService.isLikeWithPost(postId, userId)) {
			throw new NotFoundException(ResponseConstants.NOTFOUND_LIKE_CODE);
		}

		likeService.deleteLikeToPost(postId, userId);

		Optional<Post> post = getById(postId);

		Optional<LikeCountPost> likeCountPost = likeService.countLikeWithPost(postId);

		post.get().setLikeCountPost(likeCountPost);

		return post;
	}

}
