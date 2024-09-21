package com.doctor_appointment.controller;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.payload.RequestAppUser;
import com.doctor_appointment.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/v1/app_user")
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
   private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerPatient(@RequestBody RequestAppUser request) {
        return new ResponseEntity<>(registrationService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId) {
        AppUser user = registrationService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<AppUser> updateAppUser(@RequestParam String email, @RequestBody RequestAppUser requestDto) {
        return new ResponseEntity<>(registrationService.updateAppUser(email, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        registrationService.deleteUser(userId);
        return ResponseEntity.noContent().build();

    }

}
