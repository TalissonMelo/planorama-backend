package com.liberbox.members.controller.response;

import com.liberbox.members.domain.enums.MemberType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MemberResponse(String id, String idSchedule, MemberUserResponse member) {
}
