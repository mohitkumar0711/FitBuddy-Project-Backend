package com.fitbuddy.repository;

import com.fitbuddy.model.LoggedFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoggedFoodRepository extends JpaRepository<LoggedFood, Long> {

    List<LoggedFood> findByUserId(Long userId);
}