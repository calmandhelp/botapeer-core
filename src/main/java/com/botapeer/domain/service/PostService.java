package com.botapeer.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.botapeer.domain.model.post.Post;
import com.botapeer.domain.repository.IPostRepository;
import com.botapeer.domain.service.interfaces.IPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

	private final IPostRepository postRepository;

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

}
