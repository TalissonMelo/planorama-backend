package com.liberbox.members.controller;

import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.members.domain.Member;
import com.liberbox.members.service.GetMemberService;
import com.liberbox.members.service.GetMemberUserService;
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
public class GetMembersUserController {

	private final GetMemberUserService service;

	@GetMapping("/v1/schedule/{scheduleId}/members/{memberId}")
	public ResponseEntity<Member> execute(@PathVariable String scheduleId, @PathVariable String memberId) {

		Member memberResponses = service.execute(scheduleId, memberId);

		return ResponseEntity.status(200).body(memberResponses);

	}
}
