package com.doctor_appointment.service;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.Doctor;
import com.doctor_appointment.payload.DoctorDTO;

public interface DoctorService {

    Doctor addDoctorDetail(DoctorDTO doctorDTO, AppUser user);

    Doctor findDoctorById(long doctorId);
}
