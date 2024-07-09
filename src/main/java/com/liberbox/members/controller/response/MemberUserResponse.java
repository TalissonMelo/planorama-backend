package com.liberbox.members.controller.response;

import com.liberbox.members.domain.enums.MemberType;

public record MemberUserResponse(String id, String nickname, String email, String phone, MemberType type) {
}
