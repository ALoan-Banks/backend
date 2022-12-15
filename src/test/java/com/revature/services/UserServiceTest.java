package com.revature.services;

import com.revature.dtos.ResetPasswordRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepositoryMock;
    private UserService userService;

    @BeforeEach
    void setUp() {
        // mock DAO object
        this.userRepositoryMock = mock(UserRepository.class);
        this.userService = new UserService(this.userRepositoryMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void resetPassword_test() {
        // mock request
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("fake@email.com","06-12-93","newPassword");

        // mock user
        User updateUser = new User(0, "fake@email.com", "fake", "fake", "password", "06-12-93", "US", "555-555-5555");

        // mockito
        when(this.userRepositoryMock.findByEmail(resetPasswordRequest.getEmail())).thenReturn(Optional.of(updateUser));
        when(this.userRepositoryMock.save(updateUser)).thenReturn(updateUser);

        // reset password test
        assertEquals(true, this.userService.resetPassword(resetPasswordRequest).getPassword().equals(resetPasswordRequest.getNewPassword()));
    }
}