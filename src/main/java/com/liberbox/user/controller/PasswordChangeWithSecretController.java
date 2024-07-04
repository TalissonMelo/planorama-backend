package com.liberbox.user.controller;

import com.liberbox.user.controller.request.PasswordChangeWithSecretRequest;
import com.liberbox.user.controller.response.CodeIsValidResponse;
import com.liberbox.user.service.ChangePasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "users")
public class PasswordChangeWithSecretController {

    private final ChangePasswordService service;

    @PutMapping("/v1/users/passwords/codes")
    public ResponseEntity<CodeIsValidResponse> execute(@RequestBody PasswordChangeWithSecretRequest request) {

        var response = service.executeOnlyWithEmail(request);

        if (response.isValid()) {
            return ResponseEntity.status(200).body(response);
        } else {
            return ResponseEntity.status(403).body(response);
        }
    }
}
