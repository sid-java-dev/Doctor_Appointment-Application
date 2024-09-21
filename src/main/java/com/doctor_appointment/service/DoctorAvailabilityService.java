package com.doctor_appointment.service;

import com.doctor_appointment.entity.Clinic;
import com.doctor_appointment.entity.Doctor;
import com.doctor_appointment.entity.DoctorAvailability;
import com.doctor_appointment.payload.DoctorAvailabilityDTO;

import java.util.List;

public interface DoctorAvailabilityService {
    List<DoctorAvailability> saveDoctorAvailability(List<DoctorAvailabilityDTO> availabilityDTO, Doctor doctor, Clinic clinic);
    List<DoctorAvailability> getDoctorAvailability(Long doctorId);
}
