package com.doctor_appointment.payload;

import com.doctor_appointment.entity.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ClinicDTO {
    private Long id;
    private String clinicName;
    private Address address;
    private LocalTime openTime;
    private LocalTime closeTime;
}
