package com.liberbox.members.controller;

import com.liberbox.members.controller.request.MemberRequest;
import com.liberbox.members.service.PostMemberService;
import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.PostUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "member")
@RestController
@RequiredArgsConstructor
public class PostMembersController {

	private final PostMemberService service;

	@PostMapping("/v1/members")
	public ResponseEntity<Void> execute(@Valid @RequestBody List<MemberRequest> request) {

		service.execute(request);

		return ResponseEntity.status(201).body(null);

	}
}
