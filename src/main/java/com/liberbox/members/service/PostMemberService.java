package com.liberbox.members.service;

import com.liberbox.members.controller.request.MemberRequest;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMemberService {

    private final MemberRepository memberRepository;

    public void execute(List<MemberRequest> request) {

        List<Member> members = toMembers(request);

        memberRepository.saveAll(members);

    }

    private List<Member> toMembers(List<MemberRequest> request) {
        return request.stream().map(memberRequest -> Member.to(memberRequest.scheduleId(),
                memberRequest.ownerId(),
                memberRequest.type())).collect(Collectors.toList());
    }
}
