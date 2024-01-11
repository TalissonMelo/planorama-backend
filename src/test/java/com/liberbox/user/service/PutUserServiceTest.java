package com.liberbox.user.service;

import com.liberbox.user.factory.UserFactory;
import com.liberbox.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PutUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PutUserService putUserService;

    @Test
    public void UpdateShouldThrowsExceptionWhenIdNotExists() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            putUserService.execute("", UserFactory.userUpdateRequest());
        });
    }

}
