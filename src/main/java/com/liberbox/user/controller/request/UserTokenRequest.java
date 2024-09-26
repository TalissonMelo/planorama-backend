package com.liberbox.user.controller.request;

import javax.validation.constraints.Email;

public record UserTokenRequest(@Email(message = "Token is mandatory") String token) {

}
