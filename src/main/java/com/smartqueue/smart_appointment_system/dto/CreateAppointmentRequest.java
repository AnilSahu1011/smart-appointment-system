package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateAppointmentRequest(

        @NotNull
        Long userId,

        @NotNull
        Long serviceId,

        @NotNull
        LocalDateTime appointmentTime
) {}
