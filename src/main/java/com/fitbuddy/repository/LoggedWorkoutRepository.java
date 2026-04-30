package com.fitbuddy.repository;

import com.fitbuddy.model.LoggedWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoggedWorkoutRepository extends JpaRepository<LoggedWorkout, Long> {

    List<LoggedWorkout> findByUserId(Long userId);
}