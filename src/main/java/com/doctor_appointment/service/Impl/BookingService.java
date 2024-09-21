package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.Booking;
import com.doctor_appointment.entity.BookingStatus;
import com.doctor_appointment.entity.Doctor;
import com.doctor_appointment.entity.Patient;
import com.doctor_appointment.exception.PastDateException;
import com.doctor_appointment.payload.BookingRequest;
import com.doctor_appointment.repository.BookingRepository;
import com.doctor_appointment.service.DoctorService;
import com.doctor_appointment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public Booking createBooking(BookingRequest request){
        // Validate the booking request
        validateBookingRequest(request);
        Doctor doctor = doctorService.findDoctorById(request.getDoctorId());
        Patient patient = patientService.findPatientById(request.getPatientId());

        // Check for overlapping bookings for the doctor
        boolean isSlotAvailable = !bookingRepository.existsByDoctorAndBookingDateAndStartTimeBetween(doctor, request.getDate(), request.getStartTime(), request.getEndTime());
        if (!isSlotAvailable) {
            throw new IllegalArgumentException("The time slot is already booked.");
        }

        Booking booking = new Booking();
        booking.setDoctor(doctor);
        booking.setPatient(patient);
        booking.setBookingDate(request.getDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus(BookingStatus.PENDING);
        // Save the booking
        return bookingRepository.save(booking);

    }
    private void validateBookingRequest(BookingRequest bookingRequest) {
        LocalDate today = LocalDate.now();
        LocalDate bookingDate = bookingRequest.getDate();

        // Check if the date is in the past
        if (bookingDate.isBefore(today)) {
            throw new PastDateException("Cannot book for a past date");
        }

        // Check if the time is valid (ensure end time is after start time)
        LocalTime startTime = bookingRequest.getStartTime();
        LocalTime endTime = bookingRequest.getEndTime();

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }

    public boolean confirmBooking(Long bookingId) {
        // Find the booking by ID
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        // Check if the booking exists and its status is PENDING
        if (booking != null && BookingStatus.PENDING.equals(booking.getStatus())) {
            // Update status to CONFIRMED
            booking.setStatus(BookingStatus.CONFIRMED);
            // Save the updated booking
            bookingRepository.save(booking);
            return true;
        }

        // Return false if the booking was not found or is not in PENDING status
        return false;
    }
}

