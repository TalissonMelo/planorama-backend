package com.liberbox.user.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.user.controller.request.UserTokenRequest;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PutUserTokenService {

    private final UserRepository repository;

    public void execute(UserTokenRequest request) {

        User user = repository.findByIdAndActive(UserContext.getCurrentUser())
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + UserContext.getCurrentUser() + " does not exist"));

        user.addProfile(request.token());

        repository.save(user);
    }

}
