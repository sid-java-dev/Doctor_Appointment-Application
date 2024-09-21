package com.doctor_appointment.security;

import com.doctor_appointment.entity.AppUser;
import com.doctor_appointment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User not found exception")
        );
        return new User(user.getUsername(),user.getPassword(),Collections.singleton(new SimpleGrantedAuthority(user.getRoleType().name())));
    }
}
