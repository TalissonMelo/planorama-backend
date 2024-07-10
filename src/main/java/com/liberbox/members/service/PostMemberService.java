package com.liberbox.members.service;

import com.liberbox.members.controller.request.MemberUserRequest;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sms.CreateUserService;
import com.liberbox.sms.SendUserService;
import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import com.liberbox.user.service.PostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PostMemberService {

    private final ScheduleRepository scheduleRepository;
    private final CreateUserService createUserService;
    private final SendUserService sendUserService;
    private final MemberRepository memberRepository;
    private final PostUserService postUserService;
    private final UserRepository userRepository;

    public void execute(MemberUserRequest request) {

        String schedule = scheduleRepository.listById(request.scheduleId());

        Member members = toMembers(request, schedule);

        memberRepository.save(members);

    }

    private Member toMembers(MemberUserRequest request, String schedule) {

        User user = userRepository.findByEmail(request.email()).orElse(null);

        if (user != null) {
            sendUserService.send(schedule, user.getNickname(), user.getPhone());
            return Member.to(request.scheduleId(), user.getId(), request.type());
        }

        UserResponse response = postUserService.execute(new UserRequest(request.email(),
                "",
                request.nickname(),
                request.phone()));

        createUserService.sendVerificationCode(schedule, request.nickname(), request.phone(), request.email());

        return Member.to(request.scheduleId(), response.id(), request.type());
    }

}
