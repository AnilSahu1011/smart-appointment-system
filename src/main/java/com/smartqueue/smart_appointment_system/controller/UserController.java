package com.smartqueue.smart_appointment_system.controller;

import com.smartqueue.smart_appointment_system.dto.UserDTO;
import com.smartqueue.smart_appointment_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    // READ ALL
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UserDTO updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDto
    ) {
        return userService.updateUser(id, userDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
