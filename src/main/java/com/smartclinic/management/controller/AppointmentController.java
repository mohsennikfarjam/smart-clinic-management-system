package com.smartclinic.management.controller;

import com.smartclinic.management.entity.Appointment;
import com.smartclinic.management.entity.Doctor;
import com.smartclinic.management.entity.Patient;
import com.smartclinic.management.service.AppointmentService;
import com.smartclinic.management.repository.DoctorRepository;
import com.smartclinic.management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody Map<String, Object> request) {
        Long doctorId = Long.valueOf(request.get("doctorId").toString());
        Long patientId = Long.valueOf(request.get("patientId").toString());
        LocalDateTime time = LocalDateTime.parse(request.get("appointmentTime").toString());

        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (doctor == null || patient == null) {
            return ResponseEntity.badRequest().body("Doctor or Patient not found");
        }

        Appointment appointment = appointmentService.bookAppointment(doctor, patient, time);
        return ResponseEntity.status(201).body(appointment);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForPatient(patientId));
    }
}
