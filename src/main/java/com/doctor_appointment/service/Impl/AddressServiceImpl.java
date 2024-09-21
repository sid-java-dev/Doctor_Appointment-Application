package com.doctor_appointment.service.Impl;

import com.doctor_appointment.entity.Address;
import com.doctor_appointment.repository.AddressRepository;
import com.doctor_appointment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);

    }
}
