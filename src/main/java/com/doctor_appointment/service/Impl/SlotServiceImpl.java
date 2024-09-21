package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.DoctorAvailability;
import com.doctor_appointment.payload.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotServiceImpl {

        // Generates time slots for a specific doctor's availability
        public List<TimeSlot> generateSlotsForAvailability(DoctorAvailability availability) {
            List<TimeSlot> slots = new ArrayList<>();
            LocalTime slotStart = availability.getStartTime();
            LocalTime slotEnd;
            int slotDurationMinutes = 30; // Slot duration in minutes

            while (slotStart.isBefore(availability.getEndTime())) {
                slotEnd = slotStart.plusMinutes(slotDurationMinutes);
                if (slotEnd.isAfter(availability.getEndTime())) {
                    break;
                }

                slots.add(new TimeSlot(availability.getDayOfWeek(), slotStart, slotEnd));
                slotStart = slotEnd;
            }

            return slots;
        }

        // Method to generate slots for all availabilities of a doctor
        public List<TimeSlot> generateSlotsForDoctor(List<DoctorAvailability> availabilities) {
            List<TimeSlot> allSlots = new ArrayList<>();

            for (DoctorAvailability availability : availabilities) {
                allSlots.addAll(generateSlotsForAvailability(availability));
            }

            return allSlots;
        }
    }






