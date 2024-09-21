package com.doctor_appointment.controller;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.UserRole;
import com.doctor_appointment.exception.AuthException;
import com.doctor_appointment.payload.DoctorDTO;
import com.doctor_appointment.service.DoctorService;
import com.doctor_appointment.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final RegistrationService registration;

    @PostMapping("add/info")
    public ResponseEntity<?> addDoctorDetails(@RequestBody DoctorDTO doctorDTO,
                                              @AuthenticationPrincipal UserDetails logInUser) {
        Optional<AppUser> opUser = registration.getUserByUsername(logInUser.getUsername());
        if (opUser.isEmpty()) {
            return new ResponseEntity<>("User not found :" + logInUser.getUsername(), HttpStatus.BAD_REQUEST);
        }
        AppUser user = opUser.get();
        if (!user.getRoleType().equals(UserRole.ROLE_DOCTOR)) {
            throw new AuthException("You are not authorized to add doctor details.");
        }
        return new ResponseEntity<>(doctorService.addDoctorDetail(doctorDTO, user), HttpStatus.CREATED);
        }
    }

