package com.liberbox.user.controller.request;

import javax.validation.constraints.NotBlank;

public record UpdateUserRequest(@NotBlank(message = "Nickname is mandatory") String nickname, String phone) {

}
