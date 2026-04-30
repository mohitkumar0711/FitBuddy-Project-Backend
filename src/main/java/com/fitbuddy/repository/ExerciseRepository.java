package com.fitbuddy.repository;

import com.fitbuddy.model.Exercise;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAll();
    List<Exercise> findByBodyPart(String bodyPart);

    @Query("SELECT DISTINCT e.bodyPart FROM Exercise e")
    List<String> findDistinctBodyParts();
}