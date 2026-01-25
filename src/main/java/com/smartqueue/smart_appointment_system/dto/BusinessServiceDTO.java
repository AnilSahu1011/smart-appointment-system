package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BusinessServiceDTO {

    private Long id;

    @NotBlank(message = "Service name is required")
    private String name;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Integer durationInMinutes;

    @NotNull(message = "Active status is required")
    private Boolean active;

    private Long branchId;

    // getters & setters
}
