package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateAppointmentDTO {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Business service id is required")
    private Long businessServiceId;

    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;
}

