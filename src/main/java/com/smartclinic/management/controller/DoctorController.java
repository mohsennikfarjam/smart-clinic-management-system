package com.smartclinic.management.controller;

import com.smartclinic.management.service.DoctorService;
import com.smartclinic.management.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{role}/{id}/availability")
    public ResponseEntity<Map<String, Object>> getAvailability(
            @PathVariable String role,
            @PathVariable Long id,
            @RequestParam String date,
            @RequestHeader(value = "Authorization", required = false) String token) {
        
        // Validate token if needed
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            if (!tokenService.validateToken(jwt)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token", "role", role));
            }
        }

        List<String> slots = doctorService.getAvailableTimeSlots(id, date);
        return ResponseEntity.ok(Map.of(
            "doctorId", id, 
            "date", date, 
            "role", role,
            "availableSlots", slots, 
            "status", "success"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String token = doctorService.login(email, password);
        if (token != null) {
            return ResponseEntity.ok(Map.of("token", token, "status", "success", "email", email));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials", "status", "failure"));
    }
}
