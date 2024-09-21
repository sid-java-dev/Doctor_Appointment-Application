package com.doctor_appointment.payload;


import com.doctor_appointment.entity.Clinic;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDTO {

    private String name;
    private String specialization;
    private String degree;
    private int yearOfExperience;
    private int age;
    private String about;
    private ClinicDTO clinic;
    private List<DoctorAvailabilityDTO> availability;

}
