package com.fitbuddy.controller;

import com.fitbuddy.model.Exercise;
import com.fitbuddy.repository.ExerciseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExerciseController {

    private final ExerciseRepository repo;

    public ExerciseController(ExerciseRepository repo) {
        this.repo = repo;
    }

    // 👇 ADD THIS METHOD HERE
    @GetMapping("/debug-count")
    public long debugCount() {
        return repo.count();
    }

    // ✅ Get all exercises
    @GetMapping("/exercises")
    public List<Exercise> getAllExercises() {
        return repo.findAll();
    }

    // ✅ Get exercises by body part
    @GetMapping("/exercises/bodypart/{bodyPart}")
    public List<Exercise> getByBodyPart(@PathVariable String bodyPart) {
        return repo.findByBodyPart(bodyPart);
    }

    // ✅ Get distinct body parts
    @GetMapping("/exercises/bodyparts")
    public List<String> getBodyParts() {
        return repo.findDistinctBodyParts();
    }

    // ✅ Log a workout (dummy)
    @PostMapping("/exercises")
    public Map<String, Object> logWorkout(@RequestBody Map<String, Object> workout) {
        return Map.of(
                "success", true,
                "message", "Workout logged successfully",
                "data", workout
        );
    }

    // ✅ Save exercise to DB
    @PostMapping("/exercise-library")
    public Exercise saveToLibrary(@RequestBody Map<String, Object> exerciseData) {

        Exercise exercise = new Exercise();

        // 🔥 SAFE CASTING (avoids null issues)
        Object nameObj = exerciseData.get("name");
        Object bodyPartObj = exerciseData.get("bodyPart");

        if (nameObj != null) {
            exercise.setName(nameObj.toString());
        }

        if (bodyPartObj != null) {
            exercise.setBodyPart(bodyPartObj.toString());
        }

        return repo.save(exercise);
    }

    // ✅ Delete exercise
    @DeleteMapping("/exercise-library/{id}")
    public void deleteFromLibrary(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // ✅ Health check
    @GetMapping("/exercises/check-db")
    public String checkDB() {
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitness_app", "root", "g16g16g17mm@")) {

            return "Connected to database: " + conn.getCatalog();

        } catch (Exception e) {
            return "Database connection error: " + e.getMessage();
        }
    }
}