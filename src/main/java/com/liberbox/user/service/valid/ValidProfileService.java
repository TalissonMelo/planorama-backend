package com.liberbox.user.service.valid;


import com.liberbox.config.domain.UserContext;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.enums.Profile;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ValidProfileService {

    private final UserRepository userRepository;

    public void execute() {

        User user = userRepository.findByIdAndActive(UserContext.getCurrentUser())
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + UserContext.getCurrentUser() + " does not exist"));

        if (!user.getProfiles().contains(Profile.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not permission to create schedule.");
        }

    }
}
