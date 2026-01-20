package com.smartqueue.smart_appointment_system.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentResponseDTO {

    private Long appointmentId;
    private Long userId;
    private Long businessServiceId;
    private Integer tokenNumber;
    private String status;
    private LocalDateTime appointmentTime;
}
