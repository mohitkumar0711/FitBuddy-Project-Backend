package com.fitbuddy.controller;

import com.fitbuddy.model.LoggedFood;
import com.fitbuddy.repository.LoggedFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logged-food")
@CrossOrigin(origins = "*")
public class LoggedFoodController {

    @Autowired
    private LoggedFoodRepository repository;

    @PostMapping
    public LoggedFood saveFood(@RequestBody LoggedFood food) {
        return repository.save(food);
    }

    @GetMapping("/{userId}")
    public List<LoggedFood> getFoods(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        repository.deleteById(id);
    }
}