package com.smartclinic.management.controller;

import com.smartclinic.management.entity.Prescription;
import com.smartclinic.management.repository.PrescriptionRepository;
import com.smartclinic.management.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestBody Prescription prescription,
            @RequestHeader("Authorization") String token) {
        
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            if (!tokenService.validateToken(jwt)) {
                return ResponseEntity.status(401).body("Invalid token");
            }
        } else {
            return ResponseEntity.status(401).body("Authorization header missing or invalid");
        }

        if (prescription.getMedication() == null || prescription.getMedication().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Medication is required"));
        }

        Prescription saved = prescriptionRepository.save(prescription);
        return ResponseEntity.status(201).body(Map.of(
                "message", "Prescription saved successfully",
                "id", saved.getId()
        ));
    }
}
