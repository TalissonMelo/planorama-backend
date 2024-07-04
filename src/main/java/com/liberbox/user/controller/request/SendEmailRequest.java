package com.liberbox.user.controller.request;

import javax.validation.constraints.Email;

public record SendEmailRequest(@Email String email) {
}
