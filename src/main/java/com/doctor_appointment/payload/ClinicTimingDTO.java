package com.doctor_appointment.payload;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;

@Getter
@Setter
public class ClinicTimingDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Map<DayOfWeek, String> weeklyHours = new EnumMap<>(DayOfWeek.class);
}