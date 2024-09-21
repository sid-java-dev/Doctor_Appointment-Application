package com.doctor_appointment.service.Impl;

import com.doctor_appointment.exception.AuthException;
import com.doctor_appointment.payload.LoginDto;
import com.doctor_appointment.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authUser(LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authenticationManager.authenticate(authRequest);
            if (authRequest != null) {
                SecurityContextHolder.getContext().setAuthentication(authRequest);
                return "Login successful";
            }
        } catch (Exception e) {
            throw new AuthException("Invalid credentials");
        }
        return null;
    }
}
