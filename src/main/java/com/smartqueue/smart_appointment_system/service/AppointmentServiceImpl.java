package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.AppointmentResponseDTO;
import com.smartqueue.smart_appointment_system.dto.CreateAppointmentDTO;
import com.smartqueue.smart_appointment_system.entity.Appointment;
import com.smartqueue.smart_appointment_system.entity.QueueToken;
import com.smartqueue.smart_appointment_system.entity.StatusHistory;
import com.smartqueue.smart_appointment_system.entity.User;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.smartqueue.smart_appointment_system.entity.BusinessService;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository businessServiceRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final QueueTokenRepository queueTokenRepository;
    private final Environment env;

    @Override
    @Transactional
    public AppointmentResponseDTO createAppointment(CreateAppointmentDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(env.getProperty("error.user.not.found")));

        BusinessService service =
                businessServiceRepository.findById(dto.getBusinessServiceId())
                        .orElseThrow(() -> new ResourceNotFoundException(env.getProperty("error.service.not.found")));

        Appointment appointment = Appointment.builder()
                .customer(user)
                .businessService(service)
                .appointmentTime(dto.getAppointmentTime())
                .currentStatus("SCHEDULED")
                .build();

        appointment = appointmentRepository.save(appointment);

        // token generation
        LocalDate today = LocalDate.now();
        int lastToken = queueTokenRepository
                .findMaxTokenForBusinessServiceAndDate(
                        dto.getBusinessServiceId(), today
                );

        QueueToken token = QueueToken.builder()
                .appointment(appointment)
                .businessService(service)
                .queueDate(today)
                .tokenNumber(lastToken + 1)
                .active(true)
                .build();

        queueTokenRepository.save(token);
        appointment.setQueueToken(token);

        // status history
        statusHistoryRepository.save(
                StatusHistory.builder()
                        .appointment(appointment)
                        .status("SCHEDULED")
                        .changedAt(LocalDateTime.now())
                        .build()
        );

        return mapToResponseDto(appointment);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));
        return mapToResponseDto(appointment);
    }

    @Override
    @Transactional
    public Appointment updateStatus(Long appointmentId, String status) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setCurrentStatus(status);

        StatusHistory history = StatusHistory.builder()
                .appointment(appointment)
                .status(status)
                .changedAt(LocalDateTime.now())
                .build();

        statusHistoryRepository.save(history);

        return appointmentRepository.save(appointment);
    }


    /* ---------- mapping ---------- */

    private AppointmentResponseDTO mapToResponseDto(Appointment a) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setAppointmentId(a.getId());
        dto.setUserId(a.getCustomer().getId());
        dto.setBusinessServiceId(a.getBusinessService().getId());
        dto.setStatus(a.getCurrentStatus());
        dto.setAppointmentTime(a.getAppointmentTime());
        dto.setTokenNumber(
                a.getQueueToken() != null
                        ? a.getQueueToken().getTokenNumber()
                        : null
        );
        return dto;
    }
}

