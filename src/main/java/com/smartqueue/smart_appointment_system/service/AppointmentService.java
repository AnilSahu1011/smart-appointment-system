package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.AppointmentResponseDTO;
import com.smartqueue.smart_appointment_system.dto.CreateAppointmentDTO;
import com.smartqueue.smart_appointment_system.entity.Appointment;

import java.time.LocalDateTime;

public interface AppointmentService {

    Appointment updateStatus(Long appointmentId, String status);
    AppointmentResponseDTO createAppointment(CreateAppointmentDTO dto);

    AppointmentResponseDTO getAppointmentById(Long id);
}
