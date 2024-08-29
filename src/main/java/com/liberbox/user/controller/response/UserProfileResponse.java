package com.liberbox.user.controller.response;

import com.liberbox.user.domain.enums.Profile;

import java.util.Set;

public record UserProfileResponse(String id, Set<Profile> profiles) {

}
