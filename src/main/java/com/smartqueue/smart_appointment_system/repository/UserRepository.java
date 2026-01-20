package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}

