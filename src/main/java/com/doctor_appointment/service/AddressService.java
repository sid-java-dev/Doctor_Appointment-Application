package com.doctor_appointment.service;

import com.doctor_appointment.entity.Address;
import org.springframework.stereotype.Service;


public interface AddressService {
    Address createAddress(Address address);
}
