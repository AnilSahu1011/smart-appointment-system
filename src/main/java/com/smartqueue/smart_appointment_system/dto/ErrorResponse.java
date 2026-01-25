package com.smartqueue.smart_appointment_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String error;
    private Map<String, String> fieldErrors;
    private LocalDateTime timestamp;
}
