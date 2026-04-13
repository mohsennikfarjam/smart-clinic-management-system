# Database Schema Design - Smart Clinic Management System

## MySQL Database Design

### Overview
The Smart Clinic Management System uses a relational database to manage users, doctors, patients, and appointments.

### Tables

#### 1. Doctors
Stores information about the clinic's medical staff.
- `id` (INT, PK, AI)
- `first_name` (VARCHAR(50))
- `last_name` (VARCHAR(50))
- `specialization` (VARCHAR(100))
- `email` (VARCHAR(100), UNIQUE)
- `phone` (VARCHAR(20))
- `available_times` (VARCHAR(500)) -- Stored as a delimited string or in a join table

#### 2. Patients
Stores patient personal and contact information.
- `id` (INT, PK, AI)
- `first_name` (VARCHAR(50))
- `last_name` (VARCHAR(50))
- `email` (VARCHAR(100), UNIQUE)
- `phone` (VARCHAR(20))
- `date_of_birth` (DATE)

#### 3. Appointments
Manages scheduled consultations between patients and doctors.
- `id` (INT, PK, AI)
- `doctor_id` (INT, FK -> Doctors.id)
- `patient_id` (INT, FK -> Patients.id)
- `appointment_time` (DATETIME)
- `status` (VARCHAR(20)) -- e.g., SCHEDULED, CANCELLED, COMPLETED

#### 4. Prescriptions
Stores medical prescriptions issued during appointments.
- `id` (INT, PK, AI)
- `appointment_id` (INT, FK -> Appointments.id)
- `medication` (VARCHAR(255))
- `dosage` (VARCHAR(100))
- `date_issued` (DATE)

### Relationships
- **Doctor 1:N Appointment**: One doctor can have many appointments.
- **Patient 1:N Appointment**: One patient can have many appointments.
- **Appointment 1:1 Prescription**: An appointment can have one prescription.
