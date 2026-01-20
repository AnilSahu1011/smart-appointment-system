package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateAppointmentRequest(
        Long userId,
        Long businessServiceId,
        LocalDateTime appointmentTime
) {}

