package com.doctor_appointment.service;

import com.doctor_appointment.payload.LoginDto;

public interface AuthService {
    String authUser(LoginDto loginDto);
}
