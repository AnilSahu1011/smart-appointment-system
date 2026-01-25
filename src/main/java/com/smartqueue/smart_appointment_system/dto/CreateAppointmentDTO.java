package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateAppointmentDTO {

    @NotNull(message = "{validation.appointment.userId.required}")
    private Long userId;

    @NotNull(message = "{validation.appointment.businessServiceId.required}")
    private Long businessServiceId;

    @NotNull(message = "{validation.appointment.time.required}")
    @Future(message = "{validation.appointment.time.future}")
    private LocalDateTime appointmentTime;
}

