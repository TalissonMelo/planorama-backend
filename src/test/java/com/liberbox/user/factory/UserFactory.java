package com.liberbox.user.factory;

import com.liberbox.user.controller.request.UpdateUserRequest;
import com.liberbox.user.domain.User;

public class UserFactory {

    public static User createUser(){
        return User.to("usertest@liberbox.com.br", "liberBox2024@", "liberbox", "liberbox");
    }


    public static UpdateUserRequest userUpdateRequest(){
        return new UpdateUserRequest("Fernando", "");
    }
}
