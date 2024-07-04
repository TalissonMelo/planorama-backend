package com.liberbox.members.controller;

import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.members.service.GetMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "member")
@RestController
@RequiredArgsConstructor
public class GetMembersController {

	private final GetMemberService service;

	@GetMapping("/v1/schedule/{scheduleId}/members")
	public ResponseEntity<List<MemberResponse>> execute(@PathVariable String scheduleId) {

		List<MemberResponse> memberResponses = service.execute(scheduleId);

		return ResponseEntity.status(200).body(memberResponses);

	}
}
