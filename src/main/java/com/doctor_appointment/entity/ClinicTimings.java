package com.doctor_appointment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "clinic_time")
public class ClinicTimings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime openTime;
    private LocalTime closeTime;

    @OneToOne
    @JoinColumn(name = "clinic_id") // Foreign key to the clinic
    private Clinic clinic;

}
