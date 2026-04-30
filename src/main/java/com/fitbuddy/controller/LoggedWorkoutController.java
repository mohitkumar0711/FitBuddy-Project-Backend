package com.fitbuddy.controller;

import com.fitbuddy.model.LoggedWorkout;
import com.fitbuddy.repository.LoggedWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logged-workouts")
@CrossOrigin(origins = "*")
public class LoggedWorkoutController {

    @Autowired
    private LoggedWorkoutRepository repository;

    @PostMapping
    public LoggedWorkout saveWorkout(@RequestBody LoggedWorkout workout) {
        return repository.save(workout);
    }

    @GetMapping("/{userId}")
    public List<LoggedWorkout> getWorkouts(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        repository.deleteById(id);
    }
}