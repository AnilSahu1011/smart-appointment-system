package com.smartqueue.smart_appointment_system.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long appointmentId,
        String serviceName,
        Integer tokenNumber,
        String status,
        LocalDateTime appointmentTime
) {}
