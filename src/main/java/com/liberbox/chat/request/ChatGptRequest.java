package com.liberbox.chat.request;

import java.util.List;

public record ChatGptRequest(String model, List<Message> messages)  {

}