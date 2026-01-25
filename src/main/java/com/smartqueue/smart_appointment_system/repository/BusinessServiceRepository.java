package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessServiceRepository
        extends JpaRepository<BusinessService, Long> {

    List<BusinessService> findByBranchId(Long branchId);
}
