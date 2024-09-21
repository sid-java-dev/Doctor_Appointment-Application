package com.doctor_appointment.repository;

import com.doctor_appointment.entity.Address;
import com.doctor_appointment.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Optional<Clinic> findByAddress(Address address);
}