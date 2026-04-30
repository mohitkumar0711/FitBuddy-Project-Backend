package com.fitbuddy.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/calculate")
@CrossOrigin(origins = "*")
public class ToolsController {

    @PostMapping("/bmi")
    public Map<String, Object> bmi(@RequestBody Map<String, Double> data) {
        double height = data.get("heightCm");
        double weight = data.get("weightKg");
        double bmi = weight / Math.pow(height / 100, 2);
        
        String category;
        String color;
        
        if (bmi < 18.5) {
            category = "Underweight";
            color = "#3b6ef8";
        } else if (bmi < 25) {
            category = "Normal weight";
            color = "#22c55e";
        } else if (bmi < 30) {
            category = "Overweight";
            color = "#f97316";
        } else {
            category = "Obese";
            color = "#ef4444";
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("bmi", bmi);
        response.put("category", category);
        response.put("color", color);
        
        return response;
    }

    @PostMapping("/tdee")
    public Map<String, Object> tdee(@RequestBody Map<String, Object> data) {
        int age = ((Number) data.get("age")).intValue();
        double weight = ((Number) data.get("weightKg")).doubleValue();
        double height = ((Number) data.get("heightCm")).doubleValue();
        String gender = (String) data.get("gender");
        double activity = ((Number) data.get("activityFactor")).doubleValue();
        
        double bmr = gender.equals("male")
                ? 10 * weight + 6.25 * height - 5 * age + 5
                : 10 * weight + 6.25 * height - 5 * age - 161;
        
        double tdee = bmr * activity;
        
        Map<String, Object> response = new HashMap<>();
        response.put("bmr", bmr);
        response.put("tdee", tdee);
        
        return response;
    }

    @PostMapping("/bodyfat")
    public Map<String, Object> bodyFat(@RequestBody Map<String, Object> data) {
        String gender = (String) data.get("gender");
        double neck = ((Number) data.get("neckCm")).doubleValue();
        double waist = ((Number) data.get("waistCm")).doubleValue();
        double hip = data.containsKey("hipCm") ? ((Number) data.get("hipCm")).doubleValue() : 0;
        double height = ((Number) data.get("heightCm")).doubleValue();
        
        double bodyFat;
        
        if (gender.equals("male")) {
            bodyFat = 495 / (1.0324 - 0.19077 * Math.log10(waist - neck)
                    + 0.15456 * Math.log10(height)) - 450;
        } else {
            bodyFat = 495 / (1.29579 - 0.35004 * Math.log10(waist + hip - neck)
                    + 0.22100 * Math.log10(height)) - 450;
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("bodyFatPercent", Math.max(0, bodyFat));
        
        return response;
    }

    @PostMapping("/water")
    public Map<String, Object> water(@RequestBody Map<String, Object> data) {
        double weight = ((Number) data.get("weightKg")).doubleValue();
        String activity = (String) data.get("activityLevel");
        
        double base = weight * 0.033;
        if (activity.equals("high")) base += 1;
        if (activity.equals("moderate")) base += 0.5;
        
        Map<String, Object> response = new HashMap<>();
        response.put("liters", base);
        
        return response;
    }
}