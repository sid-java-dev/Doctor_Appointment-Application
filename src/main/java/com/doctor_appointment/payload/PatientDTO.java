package com.doctor_appointment.payload;

import com.doctor_appointment.entity.Address;
import lombok.Data;

@Data
public class PatientDTO {
    private String patientName;
    private int age;
    private String gender;
    private String emergencyContact;
    private Address address;
}
