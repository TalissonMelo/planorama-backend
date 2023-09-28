package com.liberbox.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.service.UserCreateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserCreateController {

	private final UserCreateService service;

	@PostMapping("/v1/users")
	public ResponseEntity<Void> execute(@RequestBody UserRequest request) {

		service.execute(request);

		return ResponseEntity.status(201).body(null);

	}
}
