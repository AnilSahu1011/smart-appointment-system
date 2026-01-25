package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchDTO {

    private Long id;

    @NotBlank(message = "{validation.branch.name.required}")
    private String name;

    @NotBlank(message = "{validation.branch.address.required}")
    private String address;

    @NotNull(message = "{validation.branch.active.required}")
    private Boolean active;
}
