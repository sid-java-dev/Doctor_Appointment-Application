package com.doctor_appointment.repository;

import com.doctor_appointment.entity.ClinicTimings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicTimingsRepository extends JpaRepository<ClinicTimings, Long> {
}