package com.doctor_appointment.payload;

import lombok.Data;

@Data
public class AppUserDto {

    private String roleType;
    private String email;
    private String password;
}
