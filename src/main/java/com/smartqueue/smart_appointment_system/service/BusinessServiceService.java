package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.entity.BusinessService;
import com.smartqueue.smart_appointment_system.entity.BusinessService;

import java.util.List;

public interface BusinessServiceService {

    BusinessService createService(BusinessService service, Long branchId);

    List<BusinessService> getAllServices();

    List<BusinessService> getServicesByBranch(Long branchId);

    BusinessService getServiceById(Long id);
}
