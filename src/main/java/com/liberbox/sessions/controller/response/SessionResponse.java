package com.liberbox.sessions.controller.response;

import java.time.LocalDateTime;

public record SessionResponse(String id, String scheduleId, ColorResponse color, String title, LocalDateTime start,
                              LocalDateTime end, String description) {
}
