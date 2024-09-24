package com.liberbox.members.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.controller.request.MemberUserRequest;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import com.liberbox.notifications.service.PostUserNotificationService;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import com.liberbox.user.service.PostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMemberService {

    private final PostUserNotificationService postUserNotificationService;
    private final ScheduleRepository scheduleRepository;
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
            postUserNotificationService.execute(UserContext.getCurrentUser(), user.getId(), schedule);
            return Member.to(request.scheduleId(), user.getId(), request.type());
        }

        UserResponse response = postUserService.execute(new UserRequest(request.email(),
                "",
                request.nickname(),
                request.phone()));

        //Notificar por email cadastro??

        return Member.to(request.scheduleId(), response.id(), request.type());
    }

}
