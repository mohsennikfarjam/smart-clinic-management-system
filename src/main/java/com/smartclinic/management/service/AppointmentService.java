package com.smartclinic.management.service;

import com.smartclinic.management.entity.Appointment;
import com.smartclinic.management.entity.Doctor;
import com.smartclinic.management.entity.Patient;
import com.smartclinic.management.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Doctor doctor, Patient patient, LocalDateTime time) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(time);
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForDoctorOnDate(Doctor doctor, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByDoctorAndAppointmentTimeBetween(doctor, start, end);
    }
    
    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
