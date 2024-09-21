package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.Clinic;
import com.doctor_appointment.entity.Doctor;
import com.doctor_appointment.entity.DoctorAvailability;
import com.doctor_appointment.exception.ClinicException;
import com.doctor_appointment.payload.DoctorAvailabilityDTO;
import com.doctor_appointment.repository.ClinicRepository;
import com.doctor_appointment.repository.DoctorAvailabilityRepository;
import com.doctor_appointment.service.DoctorAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {
    private final DoctorAvailabilityRepository availabilityRepo;
    private final ClinicRepository clinicRepo; // Repository to fetch clinic details

    public List<DoctorAvailability> saveDoctorAvailability(List<DoctorAvailabilityDTO> availabilityDTOs, Doctor doctor, Clinic clinic) {
        // Fetch the clinic timings
        Clinic clinicDetails = clinicRepo.findById(clinic.getId())
                .orElseThrow(() -> new ClinicException("Clinic not found"));

        List<DoctorAvailability> availabilities = availabilityDTOs.stream()
                .filter(dto -> isWithinClinicHours(dto, clinicDetails))
                .map(dto -> {
                    DoctorAvailability availability = new DoctorAvailability();
                    availability.setDayOfWeek(dto.getDayOfWeek());
                    availability.setStartTime(dto.getStartTime());
                    availability.setEndTime(dto.getEndTime());
                    availability.setDoctor(doctor);
                    availability.setClinic(clinic);
                    return availability;
                })
                .collect(Collectors.toList());

        return  availabilityRepo.saveAll(availabilities);
    }

    private boolean isWithinClinicHours(DoctorAvailabilityDTO dto, Clinic clinic) {
        // Check if the doctor's availability is within clinic hours
        return !dto.getStartTime().isBefore(clinic.getOpenTime()) &&
                !dto.getEndTime().isAfter(clinic.getCloseTime());
    }

    public List<DoctorAvailability> getDoctorAvailability(Long doctorId) {
        return availabilityRepo.findByDoctorId(doctorId);
    }
}

