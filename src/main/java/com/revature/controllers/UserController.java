package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://aloanbanks-frontend-bucket.s3-website-us-east-1.amazonaws.com"}, allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

//    @Authorized
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int accountId) {
        Optional<User> optional = userService.getById(accountId);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

}
