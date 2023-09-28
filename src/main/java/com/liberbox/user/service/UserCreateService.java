package com.liberbox.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class UserCreateService {

	private final UserRepository repository;

	public void execute(UserRequest request) {
		User user = User.to(request.email(), request.password(), request.nickname(), request.photo());
		repository.save(user);
	}
}
