package com.liberbox.members.service;

import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberUserService {

    private final MemberRepository memberRepository;

    public Member execute(String scheduleId, String memberId) {

        return memberRepository.listMemberToScheduleIdAndMember(scheduleId, memberId);

    }
}
