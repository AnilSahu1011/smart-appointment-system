package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchDTO {

    private Long id;

    @NotBlank(message = "Branch name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
