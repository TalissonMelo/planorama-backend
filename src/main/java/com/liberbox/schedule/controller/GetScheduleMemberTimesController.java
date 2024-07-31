package com.liberbox.schedule.controller;

import com.liberbox.schedule.controller.response.ScheduleFreeTimeResponse;
import com.liberbox.schedule.service.GetScheduleMemberTimesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class GetScheduleMemberTimesController {

    private final GetScheduleMemberTimesService service;

    @GetMapping("/v1/schedules/free-times")
    public ResponseEntity<List<ScheduleFreeTimeResponse>> execute(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Integer minutes) {

        List<ScheduleFreeTimeResponse> response = service.execute(date, minutes);

        return ResponseEntity.status(200).body(response);

    }
}
