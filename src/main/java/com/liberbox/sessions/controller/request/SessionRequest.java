package com.liberbox.sessions.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public record SessionRequest(@NotBlank String scheduleId, @NotBlank String legendId, @NotBlank String title,
                             @NotNull LocalDateTime startTime,
                             @NotNull LocalDateTime endTime, String description, List<DayOfWeek> daysOfWeeks) {
}
