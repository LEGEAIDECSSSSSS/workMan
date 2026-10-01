package com.workman.service;

import com.workman.dto.RegisterRequest;
import com.workman.model.Role;
import com.workman.model.User;
import com.workman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.valueOf(request.getRole()))
                .isActive(true)
                .build();

        return userRepository.save(user);
    }
}