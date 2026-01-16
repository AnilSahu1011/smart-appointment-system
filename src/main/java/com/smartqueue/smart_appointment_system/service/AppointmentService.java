package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.entity.Appointment;

import java.time.LocalDateTime;

public interface AppointmentService {
    Appointment createAppointment(
            Long userId,
            Long serviceId,
            LocalDateTime appointmentTime
    );

    Appointment updateStatus(Long appointmentId, String status);
}
