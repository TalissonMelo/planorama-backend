package com.liberbox.sessions.controller.request;

import javax.validation.constraints.NotBlank;

public record PutSessionRequest(@NotBlank String description) {
}
