package com.smartqueue.smart_appointment_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "{validation.user.fullName.required}")
    private String fullName;

    @NotBlank(message = "{validation.user.email.required}")
    @Email(message = "{validation.user.email.invalid}")
    private String email;

    @Size(min = 6, message = "{validation.user.password.size}")
    private String password;

    @NotNull(message = "{validation.user.active.required}")
    private Boolean active;
}
