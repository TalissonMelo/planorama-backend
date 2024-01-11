package com.liberbox.user.service;

import com.liberbox.user.domain.User;
import com.liberbox.user.factory.UserFactory;
import com.liberbox.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void saveUserSuccessfully(){
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(UserFactory.createUser());

        User user = userRepository.save(UserFactory.createUser());

        Assertions.assertNotNull(user);
    }
}
