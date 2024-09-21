package com.doctor_appointment.payload;


import lombok.Data;

@Data
public class AddressDTO {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
