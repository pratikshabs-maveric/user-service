package com.maveric.userservice.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserConfigurationTest {

    @InjectMocks
    private UserConfiguration userConfiguration;

    @Test
    void handleUserConfiguration() {
        BCryptPasswordEncoder bCryptPasswordEncoder = userConfiguration.passwordEncoder();
        assertNotNull(bCryptPasswordEncoder);
    }

}
