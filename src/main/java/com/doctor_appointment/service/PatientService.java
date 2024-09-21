package com.doctor_appointment.service;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.Patient;
import com.doctor_appointment.payload.PatientDTO;

public interface PatientService {
    Patient addPatientDetails(PatientDTO patientDTO, AppUser user);

    Patient findPatientById(long patientId);
}
