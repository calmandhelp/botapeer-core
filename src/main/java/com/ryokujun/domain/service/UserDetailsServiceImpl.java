package com.ryokujun.domain.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ryokujun.domain.entity.User;
import com.ryokujun.domain.entity.UserDetailsImpl;
import com.ryokujun.domain.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByNameOrEmail(usernameOrEmail);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(usernameOrEmail + "が存在しません");
		}
		return new UserDetailsImpl(user.get());
	}

	public UserDetails loadUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(userId + "が存在しません");
		}
		return new UserDetailsImpl(user.get());
	}
}
