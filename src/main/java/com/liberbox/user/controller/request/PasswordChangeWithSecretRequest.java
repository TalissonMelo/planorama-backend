package com.liberbox.user.controller.request;

public record PasswordChangeWithSecretRequest(String code, String email, String newPassword) {
}
