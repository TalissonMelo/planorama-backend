package com.liberbox.user.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserRequest(@Email(message = "Email is mandatory") String email,
		@NotBlank(message = "Password is mandatory") String password, 
		@NotBlank(message = "Nickname is mandatory") String nickname, String phone) {

}
