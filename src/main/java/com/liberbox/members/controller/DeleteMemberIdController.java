package com.liberbox.members.controller;

import com.liberbox.members.service.DeleteMemberIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "member")
@RestController
@RequiredArgsConstructor
public class DeleteMemberIdController {

    private final DeleteMemberIdService service;

    @DeleteMapping("/v1/members/{memberId}")
    public ResponseEntity<Void> execute(@PathVariable String memberId) {

        service.execute(memberId);

        return ResponseEntity.noContent().build();

    }
}
