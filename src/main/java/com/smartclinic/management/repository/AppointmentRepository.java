package com.smartclinic.management.repository;

import com.smartclinic.management.entity.Appointment;
import com.smartclinic.management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientId(Long patientId);
}
