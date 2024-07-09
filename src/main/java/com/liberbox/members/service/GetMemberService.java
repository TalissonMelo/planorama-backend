package com.liberbox.members.service;

import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.members.controller.response.MemberUserResponse;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public List<MemberResponse> execute(String scheduleId) {

        List<Member> members = memberRepository.listMemberToScheduleId(scheduleId);

        List<User> users = userRepository.findAllById(members.stream().map(member -> member.getOwnerId()).collect(Collectors.toList()));

        return members.stream().map(member -> new MemberResponse(member.getId(),
                member.getScheduleId(),
                toMemberUser(member, users))
        ).collect(Collectors.toList());

    }

    private MemberUserResponse toMemberUser(Member member, List<User> users) {
        return users.stream()
                .filter(user -> user.getId().equals(member.getOwnerId()))
                .map(user -> new MemberUserResponse(user.getId(), user.getNickname(), user.getEmail(), user.getPhone(), member.getType())).findFirst()
                .orElse(null);
    }


}
