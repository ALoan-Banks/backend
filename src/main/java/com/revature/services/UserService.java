package com.revature.services;

import com.revature.dtos.ResetPasswordRequest;
import com.revature.exceptions.EmailDoesntExistException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(int id) {
        return userRepository.getById(id);
    }

    public Optional<User> getById(int id) {return userRepository.findById(id);}

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User resetPassword(ResetPasswordRequest resetPasswordRequest) {
        // get user from database
        User updateUser = userRepository.findByEmail(resetPasswordRequest.getEmail()).isPresent()?userRepository.findByEmail(resetPasswordRequest.getEmail()).get():null;

        if(updateUser==null){
            throw new EmailDoesntExistException("Email doesn't exist");
        }

        // check DOB matches with user
        if(updateUser.getDOB().equalsIgnoreCase(resetPasswordRequest.getDOB())){
            updateUser.setPassword(resetPasswordRequest.getNewPassword());
        }

        // return user
        return userRepository.save(updateUser);
    }
}
