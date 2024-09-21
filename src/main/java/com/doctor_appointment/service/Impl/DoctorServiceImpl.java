package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.Clinic;
import com.doctor_appointment.entity.Doctor;
import com.doctor_appointment.entity.DoctorAvailability;
import com.doctor_appointment.exception.DoctorException;
import com.doctor_appointment.payload.DoctorAvailabilityDTO;
import com.doctor_appointment.payload.DoctorDTO;
import com.doctor_appointment.repository.DoctorRepository;
import com.doctor_appointment.service.ClinicService;
import com.doctor_appointment.service.DoctorAvailabilityService;
import com.doctor_appointment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

   private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final ClinicService clinicService;
    private final DoctorAvailabilityService doctorAvailabilityService;

    @Override
    public Doctor addDoctorDetail(DoctorDTO doctorDTO, AppUser user) {
        Clinic clinic=clinicService.createOrGetClinic(doctorDTO.getClinic());
        if(clinic!=null){
            Doctor doctor=mapToEntity(doctorDTO);
            doctor.setAppUser(user);
            doctor.setClinic(clinic);
            // Save the doctor to the database
            Doctor savedDoctor = doctorRepository.save(doctor);

            //  Save the doctor availability

            List<DoctorAvailabilityDTO> availabilityDTOs = doctorDTO.getAvailability();
            if (availabilityDTOs != null && !availabilityDTOs.isEmpty()) {
                List<DoctorAvailability> savedAvailabilities = doctorAvailabilityService.saveDoctorAvailability(availabilityDTOs, savedDoctor, clinic);
                savedDoctor.setDoctorAvailabilities(savedAvailabilities);
            }
            return savedDoctor;
        }

        throw new DoctorException("Doctor cannot be created without clinic.");
    }

    @Override
    public Doctor findDoctorById(long doctorId) {
        return doctorRepository.findById(doctorId).get();
    }

    public Doctor mapToEntity(DoctorDTO doctorDTO) {
          modelMapper.typeMap(DoctorDTO.class,Doctor.class)
                .addMappings(mapper->mapper.skip(Doctor::setClinic));
          return modelMapper.map(doctorDTO,Doctor.class);
    }

}
