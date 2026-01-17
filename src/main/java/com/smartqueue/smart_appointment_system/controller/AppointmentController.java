package com.smartqueue.smart_appointment_system.controller;

import com.smartqueue.smart_appointment_system.dto.AppointmentResponse;
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
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello from controller", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Valid @RequestBody CreateAppointmentRequest request
    ) {

        Appointment appointment = appointmentService.createAppointment(
                request.userId(),
                request.serviceId(),
                request.appointmentTime()
        );

        AppointmentResponse response = new AppointmentResponse(
                appointment.getId(),
                appointment.getService().getName(),
                appointment.getQueueToken().getTokenNumber(),
                appointment.getCurrentStatus(),
                appointment.getAppointmentTime()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
