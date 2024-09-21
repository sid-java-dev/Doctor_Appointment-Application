package com.doctor_appointment.entity;

import java.util.ArrayList;
import java.util.List;

public enum TimeSlotEnum {
    TIME_0900("9:00 AM"),
    TIME_0930("9:30 AM"),
    TIME_1000("10:00 AM"),
    TIME_1030("10:30 AM"),
    TIME_1100("11:00 AM"),
    TIME_1130("11:30 AM"),
    TIME_1200("12:00 PM"),
    TIME_1230("12:30 PM"),
    TIME_1300("1:00 PM"),
    TIME_1330("1:30 PM"),
    TIME_1400("2:00 PM"),
    TIME_1430("2:30 PM"),
    TIME_1500("3:00 PM"),
    TIME_1530("3:30 PM"),
    TIME_1600("4:00 PM"),
    TIME_1630("4:30 PM"),
    TIME_1700("5:00 PM"),
    TIME_1730("5:30 PM");

    private final String time;

    TimeSlotEnum(String time) {
        this.time = time;

    }

    public String getTime() {
        return time;
    }

    public static List<String> getAllTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        for (TimeSlotEnum bookingTime : TimeSlotEnum.values()) {
            timeSlots.add(bookingTime.getTime());
        }
        return timeSlots;
    }
}
