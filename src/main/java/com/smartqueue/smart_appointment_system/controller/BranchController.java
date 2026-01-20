package com.smartqueue.smart_appointment_system.controller;

import com.smartqueue.smart_appointment_system.dto.BranchDTO;
import com.smartqueue.smart_appointment_system.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public BranchDTO create(@Valid @RequestBody BranchDTO dto) {
        return branchService.createBranch(dto);
    }

    @GetMapping
    public List<BranchDTO> getAll() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public BranchDTO getById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    @PutMapping("/{id}")
    public BranchDTO update(
            @PathVariable Long id,
            @Valid @RequestBody BranchDTO dto
    ) {
        return branchService.updateBranch(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }
}

