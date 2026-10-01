package com.workman.service;

import com.workman.dto.RegisterRequest;
import com.workman.model.Role;
import com.workman.model.User;
import com.workman.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterRequest request = RegisterRequest.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("password123")
                .role("CUSTOMER")
                .build();

        User savedUser = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .password("password123")
                .role(Role.CUSTOMER)
                .isActive(true)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User registeredUser = userService.register(request);

        assertThat(registeredUser).isNotNull();
        assertThat(registeredUser.getId()).isEqualTo(1L);
        assertThat(registeredUser.getName()).isEqualTo("John Doe");
        assertThat(registeredUser.getEmail()).isEqualTo("john@example.com");
        assertThat(registeredUser.getRole()).isEqualTo(Role.CUSTOMER);
        assertThat(registeredUser.isActive()).isTrue();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        User userPassedToRepository = userCaptor.getValue();

        assertThat(userPassedToRepository.getName()).isEqualTo("John Doe");
        assertThat(userPassedToRepository.getEmail()).isEqualTo("john@example.com");
        assertThat(userPassedToRepository.getRole()).isEqualTo(Role.CUSTOMER);
        assertThat(userPassedToRepository.isActive()).isTrue();
    }
}