package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByServiceIdAndAppointmentTimeBetween(
            Long serviceId,
            LocalDateTime start,
            LocalDateTime end
    );
}
