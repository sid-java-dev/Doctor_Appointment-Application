package com.doctor_appointment.service;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.UserRole;
import com.doctor_appointment.exception.AppUserException;
import com.doctor_appointment.payload.RequestAppUser;
import com.doctor_appointment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AppUser createPatient(RequestAppUser request) {
        AppUser user=mapToEntity(request);
        user.setRoleType(UserRole.ROLE_PATIENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public AppUser createDoctor(RequestAppUser request) {
        AppUser user=mapToEntity(request);
        user.setRoleType(UserRole.ROLE_DOCTOR);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser mapToEntity(RequestAppUser request) {
        return  modelMapper.map(request, AppUser.class);
    }

    public AppUser getUserById(long id) {
        Optional<AppUser> opUser=userRepository.findById(id);
        if(opUser.isEmpty()){
            throw new AppUserException("User not found with given Id:"+id);
        }
        return opUser.get();
    }

    public AppUser updateAppUser(String email, RequestAppUser requestDto) {
        AppUser existUser = userRepository.findByEmail(email).orElseThrow(
                ()->new AppUserException("User not found with given email"+email)
        );
        existUser.setUsername(requestDto.getUsername());
        existUser.setPassword(requestDto.getPassword());
        existUser.setEmail(requestDto.getEmail());
       return userRepository.save(existUser);
    }
}
