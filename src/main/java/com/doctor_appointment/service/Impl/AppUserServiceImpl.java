package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.exception.AppUserException;
import com.doctor_appointment.payload.AppUserDto;
import com.doctor_appointment.repository.UserRepository;
import com.doctor_appointment.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private UserRepository appUserRepository;
    private ModelMapper modelMapper;

    public AppUserServiceImpl(UserRepository appUserRepository, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }


    @Override
    public AppUser addAppUser(AppUserDto appUserDto) {
        Optional<AppUser> appUser = findByEmail(appUserDto.getEmail());
        if (appUser.isEmpty()) {
            return appUserRepository.save(mapToEntity(appUserDto));
        } else {
            throw new AppUserException("User is already exist with email id: " + appUserDto.getEmail());
        }
    }

    public AppUser mapToEntity(AppUserDto appUserDto) {
        return modelMapper.map(appUserDto, AppUser.class);
    }

}
