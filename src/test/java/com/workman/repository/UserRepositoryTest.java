package com.workman.repository;

import com.workman.model.Role;
import com.workman.model.User;
import com.workman.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {

        User user = User.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("password123")
                .role(Role.CUSTOMER)
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("John Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john@example.com");
        assertThat(foundUser.getRole()).isEqualTo(Role.CUSTOMER);
        assertThat(foundUser.isActive()).isTrue();
    }

    @Test
    void shouldFindUserByEmail() {

        User user = User.builder()
                .name("Jane Doe")
                .email("jane@example.com")
                .password("password123")
                .role(Role.TECHNICIAN)
                .isActive(true)
                .build();

        userRepository.save(user);

        User foundUser = userRepository.findByEmail("jane@example.com").orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("Jane Doe");
        assertThat(foundUser.getEmail()).isEqualTo("jane@example.com");
        assertThat(foundUser.getRole()).isEqualTo(Role.TECHNICIAN);
    }
}