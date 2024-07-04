package com.liberbox.legend.controller;

import com.liberbox.legend.controller.request.LegendRequest;
import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.service.PostLegendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "legend")
@RestController
@RequiredArgsConstructor
public class PostLegendController {

    private final PostLegendService service;

    @PostMapping("/v1/legends")
    public ResponseEntity<LegendResponse> execute(@Valid @RequestBody LegendRequest request) {

        LegendResponse response = service.execute(request);

        return ResponseEntity.status(201).body(response);

    }
}
