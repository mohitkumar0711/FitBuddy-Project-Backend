package com.fitbuddy.controller;

import com.fitbuddy.model.UserFood;
import com.fitbuddy.repository.UserFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-food")
@CrossOrigin(origins = "*")
public class UserFoodController {

    @Autowired
    private UserFoodRepository repository;

    @PostMapping
    public UserFood saveFood(@RequestBody UserFood food) {
        return repository.save(food);
    }

    @GetMapping("/{userId}")
    public List<UserFood> getFoods(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        repository.deleteById(id);
    }
}