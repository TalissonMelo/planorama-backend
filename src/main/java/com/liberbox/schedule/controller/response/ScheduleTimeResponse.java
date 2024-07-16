package com.liberbox.schedule.controller.response;

import java.time.LocalTime;

public record ScheduleTimeResponse(LocalTime startTime, LocalTime endTime) {
}
