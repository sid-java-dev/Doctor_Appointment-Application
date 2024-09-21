package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.entity.UserRole;
import com.doctor_appointment.payload.AppUserDto;
import com.doctor_appointment.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AppUserService appUserService;

    public CustomOAuth2UserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    private ModelMapper modelMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        Optional<AppUser> optionalAppUser = appUserService.findByEmail(email);
        AppUser appUser;
        if (optionalAppUser.isEmpty()) {
            appUser = new AppUser();
            appUser.setEmail(email);
            appUser.setRoleType(UserRole.valueOf("ROLE_USER"));
            appUser.setPassword(null); // No password for OAuth users
            appUserService.addAppUser(mapToDto(appUser));
        } else {
            appUser = optionalAppUser.get();
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(appUser.getRoleType().name())
                ), oAuth2User.getAttributes(), "email");
    }
    public AppUserDto mapToDto(AppUser appUser){
        return modelMapper.map(appUser, AppUserDto.class);
    }
}
