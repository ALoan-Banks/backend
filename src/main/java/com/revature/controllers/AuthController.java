package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.ResetPasswordRequest; // added this import statement
import com.revature.services.UserService; // added this import statement
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(
                0,
                registerRequest.getEmail(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPassword(),
                registerRequest.getDOB(),
                registerRequest.getAddress(),
                registerRequest.getPhone());

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }

    /**
     Handles a request to reset a user's password.
     @param resetPasswordRequest the request object containing the new password
     @return a {@code ResponseEntity} containing the updated {@code User} object,
     with a status of {@code HttpStatus.CREATED} if the password was reset successfully
     */
    @PostMapping("/reset/password")
    public ResponseEntity<User> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.resetPassword(resetPasswordRequest));
    }
}
