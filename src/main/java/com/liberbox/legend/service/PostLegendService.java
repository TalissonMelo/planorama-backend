package com.liberbox.legend.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.legend.controller.request.LegendRequest;
import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.domain.Legend;
import com.liberbox.legend.repostiory.LegendRepository;
import com.liberbox.members.controller.request.MemberRequest;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostLegendService {

    private final LegendRepository legendRepository;

    public LegendResponse execute(LegendRequest request) {

        Legend legend = Legend.to(UserContext.getCurrentUser(), request.color(), request.description());

        legendRepository.save(legend);

        return toLegend(legend);

    }

    private LegendResponse toLegend(Legend legend) {
        return new LegendResponse(legend.getId(), legend.getOwnerId(), legend.getColor(), legend.getDescription());
    }
}
