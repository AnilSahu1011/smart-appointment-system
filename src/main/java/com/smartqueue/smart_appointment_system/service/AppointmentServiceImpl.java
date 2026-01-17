package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.entity.Appointment;
import com.smartqueue.smart_appointment_system.entity.QueueToken;
import com.smartqueue.smart_appointment_system.entity.StatusHistory;
import com.smartqueue.smart_appointment_system.entity.User;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.smartqueue.smart_appointment_system.entity.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final QueueTokenRepository queueTokenRepository;

    @Override
    @Transactional
    public Appointment createAppointment(
            Long userId,
            Long serviceId,
            LocalDateTime appointmentTime
    ) {

        // 1. Fetch customer
        User customer = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2. Fetch service
        Service service = (Service) serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        // 3. Create appointment
        Appointment appointment = Appointment.builder()
                .customer(customer)
                .service(service)
                .appointmentTime(appointmentTime)
                .currentStatus("SCHEDULED")
                .build();

        appointment = appointmentRepository.save(appointment);

        // 4. Save initial status history
        StatusHistory statusHistory = StatusHistory.builder()
                .appointment(appointment)
                .status("SCHEDULED")
                .changedAt(LocalDateTime.now())
                .build();

        statusHistoryRepository.save(statusHistory);

        // 5. Generate sequential token per service per day
        LocalDate today = LocalDate.now();

        int lastToken = queueTokenRepository
                .findMaxTokenForServiceAndDate(serviceId, today);

        int nextToken = lastToken + 1;

        // 6. Create queue token
        QueueToken queueToken = QueueToken.builder()
                .appointment(appointment)
                .service(service)
                .queueDate(today)
                .tokenNumber(nextToken)
                .active(true)
                .build();

        queueTokenRepository.save(queueToken);

        // 7. Attach token to appointment (optional but clean)
        appointment.setQueueToken(queueToken);

        return appointment;
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

    private int generateTokenNumber(Long serviceId) {
        LocalDate today = LocalDate.now();

        int lastToken = queueTokenRepository
                .findMaxTokenForServiceAndDate(serviceId, today);

        return lastToken + 1;
    }
}

