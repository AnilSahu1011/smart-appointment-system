package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.entity.Branch;
import com.smartqueue.smart_appointment_system.entity.BusinessService;
import com.smartqueue.smart_appointment_system.entity.BusinessService;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.BranchRepository;
import com.smartqueue.smart_appointment_system.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceServiceImpl implements BusinessServiceService {

    private final ServiceRepository serviceRepository;
    private final BranchRepository branchRepository;

    @Override
    public BusinessService createService(BusinessService service, Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        service.setBranch(branch);
        return serviceRepository.save(service);
    }

    @Override
    public List<BusinessService> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public List<BusinessService> getServicesByBranch(Long branchId) {
        return serviceRepository.findByBranchId(branchId);
    }

    @Override
    public BusinessService getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
    }
}
