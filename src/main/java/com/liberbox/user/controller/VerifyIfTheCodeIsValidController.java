package com.liberbox.user.controller;

import com.liberbox.user.controller.request.CodeIsValidRequest;
import com.liberbox.user.controller.response.CodeIsValidResponse;
import com.liberbox.user.service.CodeIsValidService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "users")
public class VerifyIfTheCodeIsValidController {

    private final CodeIsValidService service;

    @PostMapping("/v1/users/passwords/codes")
    public ResponseEntity<CodeIsValidResponse> execute(@RequestBody CodeIsValidRequest request) {

        CodeIsValidResponse response = service.execute(request);

        if (response.isValid()) {
            return ResponseEntity.status(200).body(response);
        } else {
            return ResponseEntity.status(403).body(response);
        }
    }
}
