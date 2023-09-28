package com.liberbox.user.controller.request;

public record UserRequest(String email, String password, String nickname, String photo) {

}
