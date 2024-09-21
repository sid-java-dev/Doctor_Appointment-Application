package com.doctor_appointment.repository;

import com.doctor_appointment.entity.Booking;
import com.doctor_appointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByDoctorAndBookingDateAndStartTimeBetween(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime);
}