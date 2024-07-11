package com.liberbox.chat;

import com.liberbox.chat.response.ChatRequest;
import com.liberbox.chat.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService service;

    @PostMapping("/v1/chat")
    public String execute(@Valid @RequestBody ChatRequest request) {
        return service.chat(request.content());
    }

}