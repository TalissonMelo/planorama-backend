package com.liberbox.config.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liberbox.config.dto.PushNotificationRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class FCMService {

    private final ObjectMapper objectMapper;

    public void sendMessageToToken(PushNotificationRequestDTO request) throws InterruptedException, ExecutionException, JsonProcessingException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        log.info("Sent message to token. Device token: " + request.token() + ", " + response + " msg " + jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequestDTO request) throws JsonProcessingException {
        return getPreconfiguredMessageBuilder(request).setToken(request.token()).build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequestDTO request) throws JsonProcessingException {
        String content = objectMapper.writeValueAsString(request.notification());
        Notification notification = Notification.builder().setTitle(request.title()).setBody(content).build();
        return Message.builder().setNotification(notification);
    }
}