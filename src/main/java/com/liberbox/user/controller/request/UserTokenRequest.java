package com.liberbox.user.controller.request;

import javax.validation.constraints.NotBlank;

public record UserTokenRequest(@NotBlank(message = "Token is mandatory") String token) {

}
