package com.example.expensetracker.controller;

import com.example.expensetracker.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam("accountId") Integer accountId) {
        try {
            Object profile = profileService.getProfile(accountId);
            Map<String, Object> response = new HashMap<>();

            if (profile != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Profile get successful");
                response.put("data", profile);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "No profile found for accountId " + accountId);
                response.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("message", "An error occurred: " + e.getMessage());
            error.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> profileDetails) {
        try {
            Object updatedProfile = profileService.updateProfile(profileDetails);
            Map<String, Object> response = new HashMap<>();

            if (updatedProfile != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Profile updated successfully");
                response.put("data", updatedProfile);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.BAD_REQUEST.value());
                response.put("message", "Profile update failed");
                response.put("data", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("message", "An error occurred: " + e.getMessage());
            error.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}