package com.liberbox.legend.service;

import com.liberbox.legend.controller.request.LegendRequest;
import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.domain.Legend;
import com.liberbox.legend.repostiory.LegendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutLegendService {

    private final LegendRepository legendRepository;

    public LegendResponse execute(String legendId, LegendRequest request) {

        Legend legend = legendRepository.findById(legendId).orElseThrow(() -> new IllegalArgumentException("Legend dos not exist"));

        legend.update(request.color(), request.description());

        legendRepository.save(legend);

        return toLegend(legend);

    }

    private LegendResponse toLegend(Legend legend) {
        return new LegendResponse(legend.getId(), legend.getOwnerId(), legend.getColor(), legend.getDescription());
    }

}
