package com.liberbox.chat.service;

import com.liberbox.chat.request.ChatGptRequest;
import com.liberbox.chat.request.Message;
import com.liberbox.chat.response.ChatGptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
public class ChatGPTService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    public String chat(String content) {

        log.info("Starting Prompt");

        ChatGptRequest request = new ChatGptRequest(model, Arrays.asList(new Message("user", content)));

        log.info("Processing Prompt");
        ChatGptResponse response =
                template.postForObject(url, request, ChatGptResponse.class);

        return response.getChoices().get(0).getMessage().content();
    }
}

