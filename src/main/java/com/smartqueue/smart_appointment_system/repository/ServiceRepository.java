package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByBranchId(Long branchId);
}
