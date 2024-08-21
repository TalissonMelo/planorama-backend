package com.liberbox.legend.controller;


import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.service.GetLegendSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "legend")
@RestController
@RequiredArgsConstructor
public class GetLegendSessionController {

    private final GetLegendSessionService service;

    @GetMapping("/v1/schedules/{scheduleId}/legends")
    public ResponseEntity<List<LegendResponse>> execute(@PathVariable String scheduleId) {

        List<LegendResponse> response = service.execute(scheduleId);

        return ResponseEntity.status(200).body(response);

    }
}
