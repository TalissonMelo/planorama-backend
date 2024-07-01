package com.liberbox.members.service;

import com.liberbox.members.domain.Member;
import com.liberbox.members.domain.enums.MemberType;
import com.liberbox.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMemberCreatedScheduleService {

    private final MemberRepository memberRepository;

    public void execute(String scheduleId, String ownerId) {

        Member member = Member.to(scheduleId, ownerId, MemberType.CREATOR);

        memberRepository.save(member);

    }

}
