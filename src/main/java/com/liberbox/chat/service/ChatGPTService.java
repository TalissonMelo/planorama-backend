package com.liberbox.chat.service;

import com.liberbox.chat.domain.Chat;
import com.liberbox.chat.repository.ChatRepository;
import com.liberbox.chat.request.ChatGptRequest;
import com.liberbox.chat.request.Message;
import com.liberbox.chat.response.ChatGptResponse;
import com.liberbox.chat.response.ChatRequest;
import com.liberbox.config.domain.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ChatGPTService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    private final RestTemplate template;
    private final ChatRepository chatRepository;

    public ChatRequest chat(String sessionId, String content) {

        Chat chat = Chat.to(UserContext.getCurrentUser(), sessionId, content);

        chatRepository.save(chat);

        return new ChatRequest("Momento sem integração apenas mensagem padrão.");

        /*ChatGptRequest request = new ChatGptRequest(model, Arrays.asList(new Message("user", content)));

        ChatGptResponse response = template.postForObject(url, request, ChatGptResponse.class);

        return new ChatRequest(response.getChoices().get(0).getMessage().content());*/
    }
}

