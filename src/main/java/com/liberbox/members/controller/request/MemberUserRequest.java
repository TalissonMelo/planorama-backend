package com.liberbox.members.controller.request;

import com.liberbox.members.domain.enums.MemberType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MemberUserRequest(@Email(message = "Email is mandatory") String email,
                                @NotBlank(message = "Nickname is mandatory") String nickname,
                                @NotBlank  String phone,
                                @NotBlank String scheduleId,
                                @NotNull MemberType type) {
}
