package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.UserRole;
import com.doctor_appointment.exception.AppUserException;
import com.doctor_appointment.payload.RequestAppUser;
import com.doctor_appointment.repository.UserRepository;
import com.doctor_appointment.service.DoctorService;
import com.doctor_appointment.service.PatientService;
import com.doctor_appointment.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private static final Map<String, UserRole> ROLE_MAP = Map.of(
            "ROLE_PATIENT", UserRole.ROLE_PATIENT,
            "ROLE_DOCTOR", UserRole.ROLE_DOCTOR

    );

    public RegistrationServiceImpl(PatientService patientService, DoctorService doctorService, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser createUser(RequestAppUser request) {

        // Validate  Role
        UserRole role = ROLE_MAP.get(request.getRoleType());
        if (role == null) {
            throw new AppUserException("Invalid user role: " + request.getRoleType());
        }

        switch (role) {
            case ROLE_PATIENT:
                return saveAppUserRolePatient(request);
            case ROLE_DOCTOR:
                return saveAppUserRoleDoctor(request);
            default:
                throw new IllegalArgumentException("Invalid user role: " + request.getRoleType());
        }
    }
    public AppUser saveAppUserRolePatient(RequestAppUser request) {
        AppUser user=mapToEntity(request);
        user.setRoleType(UserRole.ROLE_PATIENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }
    public AppUser saveAppUserRoleDoctor(RequestAppUser request) {
        AppUser user=mapToEntity(request);
        user.setRoleType(UserRole.ROLE_DOCTOR);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
  @Override
    public AppUser getUserById(Long id) {
        Optional<AppUser> opUser=userRepository.findById(id);
        if(opUser.isEmpty()){
            throw new AppUserException("User not found with given Id:"+id);
        }
        return opUser.get();
    }
@Override
    public AppUser updateAppUser(String email, RequestAppUser requestDto) {
        AppUser existUser = userRepository.findByEmail(email).orElseThrow(
                ()->new AppUserException("User not found with given email"+email)
        );
        existUser.setUsername(requestDto.getUsername());
        existUser.setPassword(requestDto.getPassword());
        existUser.setEmail(requestDto.getEmail());
        return userRepository.save(existUser);
    }

    @Override
    public void deleteUser(Long userId) {
        AppUser user=userRepository.findById(userId).orElseThrow(
                ()-> new AppUserException("User not Found with given id"+ userId)
        );
        userRepository.delete(user);
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public AppUser mapToEntity(RequestAppUser request) {
        return  modelMapper.map(request, AppUser.class);
    }

}

