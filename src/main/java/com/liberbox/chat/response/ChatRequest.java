package com.liberbox.chat.response;

import javax.validation.constraints.NotBlank;

public record ChatRequest(@NotBlank String content) {
}
