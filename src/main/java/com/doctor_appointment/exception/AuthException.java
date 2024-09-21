package com.doctor_appointment.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
