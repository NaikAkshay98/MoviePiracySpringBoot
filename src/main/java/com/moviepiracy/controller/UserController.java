package com.moviepiracy.controller;

import com.moviepiracy.model.User;
import com.moviepiracy.service.UserService;
import com.moviepiracy.exception.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            // Handling the specific case where the email already exists
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // HttpStatus.CONFLICT indicates a duplicate entry
        } catch (RuntimeException e) {
            // General runtime exceptions
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        boolean isAuthenticated = userService.authenticateUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (isAuthenticated) {
            return new ResponseEntity<>("Authentication successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
