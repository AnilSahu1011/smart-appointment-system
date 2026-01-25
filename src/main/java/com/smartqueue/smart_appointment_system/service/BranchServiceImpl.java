package com.smartqueue.smart_appointment_system.service;
import com.smartqueue.smart_appointment_system.dto.BranchDTO;
import com.smartqueue.smart_appointment_system.entity.Branch;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.BranchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Environment env;

    @Override
    public BranchDTO createBranch(BranchDTO dto) {

        Branch branch = Branch.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .active(dto.getActive())
                .build();

        branch = branchRepository.save(branch);
        return modelMapper.map(branch, BranchDTO.class);
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(b->modelMapper.map(b, BranchDTO.class))
                .toList();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        return modelMapper.map(findBranch(id), BranchDTO.class);
    }

    @Override
    @Transactional
    public BranchDTO updateBranch(Long id, BranchDTO dto) {

        Branch branch = findBranch(id);
        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        branch.setActive(dto.getActive());

        return modelMapper.map(branch, BranchDTO.class);
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.delete(findBranch(id));
    }

    /* ---------- helpers ---------- */

    private Branch findBranch(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(env.getProperty("error.branch.not.found")));
    }
}
