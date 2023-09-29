package com.liberbox.user.controller.request;

import javax.validation.constraints.NotBlank;

public record ChangePasswordRequest(@NotBlank(message = "OldPassword is mandatory") String oldPassword,
		@NotBlank(message = "NewPassword is mandatory") String newPassword) {

}
