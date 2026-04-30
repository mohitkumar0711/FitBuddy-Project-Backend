package com.fitbuddy.controller;

import com.fitbuddy.model.User;
import com.fitbuddy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository repo;

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        Optional<User> user = repo.findByEmail(email);
        
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        if (!user.get().getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.get().getUser_id());
        response.put("name", user.get().getUsername());
        response.put("email", user.get().getEmail());
        response.put("token", "dummy-token-" + user.get().getUser_id()); // For demo
        
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> userData) {
        String name = userData.get("name");
        String email = userData.get("email");
        String password = userData.get("password");
        
        Optional<User> existing = repo.findByEmail(email);
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        
        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        
        User saved = repo.save(newUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getUser_id());
        response.put("name", saved.getUsername());
        response.put("email", saved.getEmail());
        response.put("token", "dummy-token-" + saved.getUser_id()); // For demo
        
        return response;
    }
}