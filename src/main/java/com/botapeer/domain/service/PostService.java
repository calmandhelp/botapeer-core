package com.botapeer.domain.service;

import java.util.Collection;

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
	public Collection<Post> findByPlantRecordId(int plantRecordId) {
		return postRepository.findByPlantRecordId(plantRecordId);
	}

}
