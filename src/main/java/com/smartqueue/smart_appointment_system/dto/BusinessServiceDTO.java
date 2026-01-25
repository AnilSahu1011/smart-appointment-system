package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BusinessServiceDTO {

    private Long id;

    @NotBlank(message = "{validation.businessService.name.required}")
    private String name;

    @NotNull(message = "{validation.businessService.duration.required}")
    @Positive(message = "{validation.businessService.duration.positive}")
    private Integer durationInMinutes;

    @NotNull(message = "{validation.businessService.active.required}")
    private Boolean active;

    private Long branchId;

    // getters & setters
}
