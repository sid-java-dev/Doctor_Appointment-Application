package com.doctor_appointment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();
       String email=oAuth2User.getAttribute("email");
        return "Hello :"+ email;
    }
}
