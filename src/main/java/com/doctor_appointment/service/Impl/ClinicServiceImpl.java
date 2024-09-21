package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.Address;
import com.doctor_appointment.entity.Clinic;
import com.doctor_appointment.entity.ClinicTimings;
import com.doctor_appointment.payload.AddressDTO;
import com.doctor_appointment.payload.ClinicDTO;
import com.doctor_appointment.payload.ClinicTimingDTO;
import com.doctor_appointment.repository.AddressRepository;
import com.doctor_appointment.repository.ClinicRepository;
import com.doctor_appointment.service.AddressService;
import com.doctor_appointment.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepo;
    private final ModelMapper modelMapper;
    private final AddressService addressService;

    @Override
    public Clinic createOrGetClinic(ClinicDTO clinicDTO) {

        //first Checked  if Clinic is already exists return that clinic
        Address address = createOrGetAddress(clinicDTO.getAddress());
        Optional<Clinic> opClinic = clinicRepo.findByAddress(clinicDTO.getAddress());
        if (opClinic.isPresent()) {
            return opClinic.get();
        }
        //save Clinic
        Clinic clinic = mapToEntity(clinicDTO);
        clinic.setAddress(address);
        return clinicRepo.save(clinic);
    }

    private Address createOrGetAddress(Address address) {
        return addressService.createAddress(address);
    }

    public Clinic mapToEntity(ClinicDTO clinicDTO) {
        return modelMapper.map(clinicDTO, Clinic.class);
    }
//    public Address mapToEntity(AddressDTO addressDTO){
//        return modelMapper.map(addressDTO, Address.class);
//    }

}
