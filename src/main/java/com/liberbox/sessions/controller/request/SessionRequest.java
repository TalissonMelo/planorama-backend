package com.liberbox.sessions.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record SessionRequest(@NotBlank String scheduleId, @NotBlank String legendId, @NotBlank String title,
                             @NotNull LocalDateTime startTime,
                             @NotNull LocalDateTime endTime, String description) {
}
