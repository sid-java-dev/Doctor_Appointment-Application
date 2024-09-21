package com.doctor_appointment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DoctorAppointmentApplication {

	public static void main(String[] args) {
	 SpringApplication.run(DoctorAppointmentApplication.class, args);

	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

}
