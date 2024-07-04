package com.liberbox.legend.service;

import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.domain.Legend;
import com.liberbox.legend.repostiory.LegendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLegendUserService {

    private final LegendRepository legendRepository;

    public List<LegendResponse> execute(String userId) {

        List<Legend> legends = legendRepository.findAllByOwnerId(userId);

        return legends.stream().map(legend -> toLegend(legend)).collect(Collectors.toList());

    }

    private LegendResponse toLegend(Legend legend) {
        return new LegendResponse(legend.getId(), legend.getOwnerId(), legend.getColor(), legend.getDescription());
    }

}
