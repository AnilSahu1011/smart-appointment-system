package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDto);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDto);

    void deleteUser(Long id);
}
