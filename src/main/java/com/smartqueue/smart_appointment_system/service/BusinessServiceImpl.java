package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.BusinessServiceDTO;
import com.smartqueue.smart_appointment_system.entity.Branch;
import com.smartqueue.smart_appointment_system.entity.BusinessService;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.BranchRepository;
import com.smartqueue.smart_appointment_system.repository.BusinessServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessServiceService {

    private final BusinessServiceRepository businessServiceRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Environment env;

    // CREATE
    @Override
    public BusinessServiceDTO createService(Long branchId, BusinessServiceDTO dto) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch not found"));

        BusinessService service =
                modelMapper.map(dto, BusinessService.class);

        service.setBranch(branch);

        BusinessService saved =
                businessServiceRepository.save(service);

        BusinessServiceDTO response =
                modelMapper.map(saved, BusinessServiceDTO.class);

        response.setBranchId(branch.getId());
        return response;
    }

    // READ ALL
    @Override
    public List<BusinessServiceDTO> getAllServices() {
        return businessServiceRepository.findAll()
                .stream()
                .map(service -> {
                    BusinessServiceDTO dto =
                            modelMapper.map(service, BusinessServiceDTO.class);
                    dto.setBranchId(service.getBranch().getId());
                    return dto;
                })
                .toList();
    }

    // READ BY ID
    @Override
    public BusinessServiceDTO getServiceById(Long id) {

        BusinessService service = findService(id);

        BusinessServiceDTO dto =
                modelMapper.map(service, BusinessServiceDTO.class);

        dto.setBranchId(service.getBranch().getId());
        return dto;
    }

    // READ BY BRANCH
    @Override
    public List<BusinessServiceDTO> getServicesByBranch(Long branchId) {

        return businessServiceRepository.findByBranchId(branchId)
                .stream()
                .map(service -> {
                    BusinessServiceDTO dto =
                            modelMapper.map(service, BusinessServiceDTO.class);
                    dto.setBranchId(service.getBranch().getId());
                    return dto;
                })
                .toList();
    }

    // UPDATE
    @Override
    @Transactional
    public BusinessServiceDTO updateService(Long id, BusinessServiceDTO dto) {

        BusinessService service = findService(id);

        service.setName(dto.getName());
        service.setDurationInMinutes(dto.getDurationInMinutes());
        service.setActive(dto.getActive());

        BusinessServiceDTO response =
                modelMapper.map(service, BusinessServiceDTO.class);

        response.setBranchId(service.getBranch().getId());
        return response;
    }

    // DELETE
    @Override
    public void deleteService(Long id) {
        businessServiceRepository.delete(findService(id));
    }

    /* -------- Helper -------- */

    private BusinessService findService(Long id) {
        return businessServiceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(env.getProperty("error.business.service.not.found")));
    }
}
