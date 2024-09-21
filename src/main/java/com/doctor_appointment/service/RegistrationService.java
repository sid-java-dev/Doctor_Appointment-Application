package com.doctor_appointment.service;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.payload.RequestAppUser;

import java.util.Optional;

public interface RegistrationService {
    AppUser createUser(RequestAppUser request);

    AppUser getUserById(Long userId);

    AppUser updateAppUser(String email, RequestAppUser requestDto);

    void deleteUser(Long userId);

    Optional<AppUser> getUserByUsername(String username);
}
