package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.QueueToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueTokenRepository extends JpaRepository<QueueToken, Long> {
}
