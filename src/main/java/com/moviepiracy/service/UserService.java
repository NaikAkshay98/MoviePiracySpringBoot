package com.moviepiracy.service;

import com.moviepiracy.model.User;
import com.moviepiracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Other service methods...

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        // Corrected method call to findByEmail
        Optional<User> existingUser = findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        // Encrypting the password here before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
