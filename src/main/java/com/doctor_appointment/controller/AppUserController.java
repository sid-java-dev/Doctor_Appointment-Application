package com.doctor_appointment.controller;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.payload.AppUserDto;
import com.doctor_appointment.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @GetMapping("/oauth")
    public ResponseEntity<AppUser> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        // Retrieve the AppUser directly from the OAuth2User attributes
        AppUser appUser = appUserService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser>addAppUser(@RequestBody AppUserDto appUserDto){
        return new ResponseEntity<>( appUserService.addAppUser(appUserDto), HttpStatus.CREATED);
    }



}
