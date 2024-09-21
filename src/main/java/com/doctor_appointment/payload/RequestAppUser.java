package com.doctor_appointment.payload;

import com.doctor_appointment.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAppUser {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String roleType;
}
