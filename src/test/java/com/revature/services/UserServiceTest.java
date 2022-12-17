package com.revature.services;

import com.revature.dtos.ResetPasswordRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    private UserService userService;
    private User tmpUser;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userRepositoryMock);

        // mock request
        this.tmpUser = new User(0, "fakefakefake@email.com", "Blunder", "Buster", "password", "02-20-84", "43 ElectricBoogalooLanes", "555-555-5555");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        // mock response
        when(this.userRepositoryMock.getById(0)).thenReturn(this.tmpUser);

        // correct user returned
        assertEquals(this.tmpUser, this.userService.findById(0));
    }

    @Test
    void getById() {
        // mockito
        when(this.userRepositoryMock.getById(this.tmpUser.getId())).thenReturn(this.tmpUser);

        // correct user retrieved
        assertEquals(true, this.userService.findById(this.tmpUser.getId()).equals(this.tmpUser));
    }

    @Test
    void findByCredentials() {
        // mockito
        when(this.userRepositoryMock.findByEmailAndPassword(this.tmpUser.getEmail(), this.tmpUser.getPassword())).thenReturn(Optional.of(this.tmpUser));

        // correct user retrieved
        assertTrue(this.userService.findByCredentials(this.tmpUser.getEmail(), this.tmpUser.getPassword()).equals(Optional.of(this.tmpUser)));
    }

    @Test
    void findByEmail() {
        // mockito
        when(this.userRepositoryMock.findByEmail(this.tmpUser.getEmail())).thenReturn(Optional.of(this.tmpUser));

        // correct user retrieved
        assertTrue(this.userService.findByEmail(this.tmpUser.getEmail()).equals(Optional.of(this.tmpUser)));
    }

    @Test
    void save() {
        // mockito
        when(this.userRepositoryMock.save(this.tmpUser)).thenReturn(this.tmpUser);

        // user should return after being saved
        assertTrue(this.userService.save(this.tmpUser).equals(this.tmpUser));
    }

    @Test
    void resetPassword_test() {
        // mock data
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("fakefakefake@email.com", "06-12-93", "newPassword");
        User updatedUser = this.tmpUser;

        // update password
        updatedUser.setPassword(resetPasswordRequest.getNewPassword());

        // mockito
        when(this.userRepositoryMock.findByEmail(this.tmpUser.getEmail())).thenReturn(Optional.of(tmpUser));
        when(this.userRepositoryMock.save(tmpUser)).thenReturn(updatedUser);

        // reset password test
        assertTrue(this.userService.resetPassword(resetPasswordRequest).getPassword().equals(resetPasswordRequest.getNewPassword()));
    }
}