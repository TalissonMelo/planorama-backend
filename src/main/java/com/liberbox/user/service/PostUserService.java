package com.liberbox.user.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.enums.Profile;
import com.liberbox.user.domain.mapper.UserMapper;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PostUserService {

	private final UserRepository repository;
	private final BCryptPasswordEncoder encoder;

	public UserResponse execute(UserRequest request) {

		User user = User.to(request.email(), encoder.encode(request.password()), request.nickname(), request.phone());

		user.addProfile(Profile.USER);

		repository.save(user);

		return UserMapper.extracted(user);
	}
}
