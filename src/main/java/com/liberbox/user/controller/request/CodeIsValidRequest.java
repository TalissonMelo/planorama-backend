package com.liberbox.user.controller.request;

import javax.validation.constraints.NotBlank;

public record CodeIsValidRequest(@NotBlank String code) {
}
