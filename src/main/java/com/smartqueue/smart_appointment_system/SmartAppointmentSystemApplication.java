package com.smartqueue.smart_appointment_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartAppointmentSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SmartAppointmentSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Successfully Started Smart Appointment System");
	}
}
