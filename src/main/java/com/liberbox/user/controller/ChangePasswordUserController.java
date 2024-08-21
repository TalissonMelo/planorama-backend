package com.liberbox.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.controller.request.ChangePasswordRequest;
import com.liberbox.user.service.ChangePasswordUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class ChangePasswordUserController {

	private final ChangePasswordUserService service;

	@PutMapping("/v1/users/{userId}/passwords")
	public ResponseEntity<Void> execute(@PathVariable String userId,@Valid @RequestBody ChangePasswordRequest request) {

		service.execute(userId, request);

		return ResponseEntity.status(200).body(null);

	}
}
