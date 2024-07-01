package com.liberbox.schedule.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public record ScheduleRequest(@NotBlank String name, @NotNull LocalTime startTime, @NotNull LocalTime endTime) {
}
