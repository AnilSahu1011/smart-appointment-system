package com.smartqueue.smart_appointment_system.controller;

import com.smartqueue.smart_appointment_system.dto.AppointmentResponse;
import com.smartqueue.smart_appointment_system.dto.AppointmentResponseDTO;
import com.smartqueue.smart_appointment_system.dto.CreateAppointmentDTO;
import com.smartqueue.smart_appointment_system.dto.CreateAppointmentRequest;
import com.smartqueue.smart_appointment_system.entity.Appointment;
import com.smartqueue.smart_appointment_system.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public AppointmentResponseDTO create(
            @Valid @RequestBody CreateAppointmentDTO dto
    ) {
        return appointmentService.createAppointment(dto);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO getById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        appointmentService.updateStatus(id, status);
        return ResponseEntity.ok("Status updated successfully");
    }
}

