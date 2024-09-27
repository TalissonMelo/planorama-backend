package com.liberbox.user.controller;

import com.liberbox.user.controller.request.SendEmailRequest;
import com.liberbox.user.controller.response.EmailResponse;
import com.liberbox.user.service.SendPhoneCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "users")
public class SendPhoneCodeController {

    private final SendPhoneCodeService sendPhoneCodeService;

    @PutMapping("/v1/users/passwords")
    public ResponseEntity<EmailResponse> execute(@RequestBody SendEmailRequest request) {

        EmailResponse response = sendPhoneCodeService.execute(request);

        return ResponseEntity.status(201).body(response);
    }
}
