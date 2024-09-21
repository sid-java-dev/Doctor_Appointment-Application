package com.doctor_appointment.controller;

import com.doctor_appointment.entity.Booking;
import com.doctor_appointment.entity.DoctorAvailability;
import com.doctor_appointment.entity.TimeSlotEnum;
import com.doctor_appointment.payload.BookingRequest;
import com.doctor_appointment.payload.TimeSlot;
import com.doctor_appointment.service.DoctorAvailabilityService;
import com.doctor_appointment.service.Impl.BookingService;
import com.doctor_appointment.service.Impl.SlotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final SlotServiceImpl slotService;
   private final  DoctorAvailabilityService availabilityService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) { // Format: HH:mm

        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/slots/{doctorId}")
    public ResponseEntity<List<TimeSlot>> getAvailableSlots(@PathVariable long doctorId) {
        List<DoctorAvailability> availabilities = availabilityService.getDoctorAvailability(doctorId);

        if (availabilities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.ok(slotService.generateSlotsForDoctor(availabilities));
    }

    @PutMapping("/{bookingId}/confirm")
    public ResponseEntity<String> confirmBooking(@PathVariable Long bookingId) {
        boolean isUpdated = bookingService.confirmBooking(bookingId);
        if (isUpdated) {
            return ResponseEntity.ok("Booking confirmed");
        } else {
            return ResponseEntity.status(404).body("Booking not found or already confirmed");
        }
    }
}




