package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.Patient;
import com.doctor_appointment.payload.PatientDTO;
import com.doctor_appointment.payload.RequestAppUser;
import com.doctor_appointment.repository.PatientRepository;
import com.doctor_appointment.repository.UserRepository;
import com.doctor_appointment.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final PatientRepository patientRepo;

    public PatientServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PatientRepository patientRepo) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.patientRepo = patientRepo;
    }

    @Override
    public Patient addPatientDetails(PatientDTO patientDTO, AppUser user) {

        Patient patient=mapToEntity(patientDTO);

        patient.setAppUser(user);
        return patientRepo.save(patient);
    }

    @Override
    public Patient findPatientById(long patientId) {
        return patientRepo.findById(patientId).get();
    }

    public Patient mapToEntity(PatientDTO patientDTO) {
        return  modelMapper.map(patientDTO,Patient.class);
    }

}
/*
private Patient convertToEntity(PatientDTO dto) {
    Patient patient = new Patient();
    patient.setPatientName(dto.getPatientName());
    patient.setAge(dto.getAge());
    patient.setGender(dto.getGender());
    patient.setEmergencyContact(dto.getEmergencyContact());

    // Convert AddressDTO to Address entity
    Address address = new Address();
    AddressDTO addressDTO = dto.getAddress();
    if (addressDTO != null) {
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setZipCode(addressDTO.getZipCode());
    }
    patient.setAddress(address);

    // Optionally set other fields as necessary
    return patient;
}
*/

/*private PatientDTO convertToDTO(Patient entity) {
    PatientDTO dto = new PatientDTO();
    dto.setPatientName(entity.getPatientName());
    dto.setAge(entity.getAge());
    dto.setGender(entity.getGender());
    dto.setEmergencyContact(entity.getEmergencyContact());

    // Convert Address entity to AddressDTO
    Address address = entity.getAddress();
    if (address != null) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreetAddress(address.getStreetAddress());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setZipCode(address.getZipCode());
        dto.setAddress(addressDTO);
    }

    // Optionally include other fields as necessary
    return dto;
}*/
