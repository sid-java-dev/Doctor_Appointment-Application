package com.doctor_appointment.service;

import com.doctor_appointment.entity.Clinic;
import com.doctor_appointment.payload.ClinicDTO;

public interface ClinicService {
    Clinic createOrGetClinic(ClinicDTO clinic);
}
