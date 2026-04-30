package com.fitbuddy.controller;

import com.fitbuddy.model.Food;
import com.fitbuddy.repository.FoodRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FoodController {

    private final FoodRepository repo;

    public FoodController(FoodRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        return repo.findAll();
    }
}