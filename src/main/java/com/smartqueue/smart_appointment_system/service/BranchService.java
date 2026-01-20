package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    BranchDTO createBranch(BranchDTO dto);

    List<BranchDTO> getAllBranches();

    BranchDTO getBranchById(Long id);

    BranchDTO updateBranch(Long id, BranchDTO dto);

    void deleteBranch(Long id);
}
