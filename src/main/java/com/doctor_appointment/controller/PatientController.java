package com.doctor_appointment.controller;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.Patient;
import com.doctor_appointment.entity.UserRole;
import com.doctor_appointment.exception.AuthException;
import com.doctor_appointment.payload.PatientDTO;
import com.doctor_appointment.service.PatientService;
import com.doctor_appointment.service.RegistrationService;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private RegistrationService registration;
    private PatientService patientService;

    public PatientController(RegistrationService registration, PatientService patientService) {
        this.registration = registration;
        this.patientService = patientService;
    }


    @PostMapping("/add/info")
    public ResponseEntity<?> addPatientDetails(@AuthenticationPrincipal UserDetails logInUser,
                                                        @RequestBody PatientDTO patientDTO){
  Optional<AppUser> opUser=registration.getUserByUsername(logInUser.getUsername());
 if(opUser.isEmpty()){
     return new ResponseEntity<>("User not found :"+logInUser.getUsername(),HttpStatus.BAD_REQUEST);
 }
    AppUser user=opUser.get();

        // Ensure that the logged-in user is a patient
        if (user.getRoleType().equals(UserRole.ROLE_PATIENT)) {
            return new ResponseEntity<>(patientService.addPatientDetails(patientDTO,user), HttpStatus.CREATED);
        }else{
            throw new AuthException("You are not authorized to add patient details.");
        }

    }
}
