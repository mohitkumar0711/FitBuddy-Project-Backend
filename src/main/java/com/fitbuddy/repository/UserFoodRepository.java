package com.fitbuddy.repository;

import com.fitbuddy.model.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {

    List<UserFood> findByUserId(Long userId);
}