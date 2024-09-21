package com.doctor_appointment.service;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.payload.AppUserDto;

import java.util.Optional;

public interface AppUserService {
    public AppUser addAppUser(AppUserDto appUserDto);
    public Optional<AppUser> findByEmail(String email);
}
