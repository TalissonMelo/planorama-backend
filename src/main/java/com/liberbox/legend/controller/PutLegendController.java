package com.liberbox.legend.controller;

import com.liberbox.legend.controller.request.LegendRequest;
import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.service.PutLegendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "legend")
@RestController
@RequiredArgsConstructor
public class PutLegendController {

    private final PutLegendService service;

    @PutMapping("/v1/legends/{legendId}")
    public ResponseEntity<LegendResponse> execute(@PathVariable String legendId,
                                                  @Valid @RequestBody LegendRequest request) {

        LegendResponse response = service.execute(legendId, request);

        return ResponseEntity.status(200).body(response);

    }
}
