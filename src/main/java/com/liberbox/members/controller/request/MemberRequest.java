package com.liberbox.members.controller.request;

import com.liberbox.members.domain.enums.MemberType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MemberRequest(@NotBlank String scheduleId, @NotBlank String ownerId, @NotNull MemberType type) {
}
