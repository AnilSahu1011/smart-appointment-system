package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {
}