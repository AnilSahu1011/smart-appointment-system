package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.BusinessServiceDTO;

import java.util.List;

public interface BusinessServiceService {

    BusinessServiceDTO createService(Long branchId, BusinessServiceDTO dto);

    List<BusinessServiceDTO> getAllServices();

    BusinessServiceDTO getServiceById(Long id);

    List<BusinessServiceDTO> getServicesByBranch(Long branchId);

    BusinessServiceDTO updateService(Long id, BusinessServiceDTO dto);

    void deleteService(Long id);
}
