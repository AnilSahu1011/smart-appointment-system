package com.smartqueue.smart_appointment_system.service;

import com.smartqueue.smart_appointment_system.dto.UserDTO;
import com.smartqueue.smart_appointment_system.entity.User;
import com.smartqueue.smart_appointment_system.exception.ResourceNotFoundException;
import com.smartqueue.smart_appointment_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Environment env;

    @Override
    public UserDTO createUser(UserDTO dto) {

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .active(dto.getActive())
                .build();

        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return modelMapper.map(findUser(id), UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {

        User user = findUser(id);

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setActive(dto.getActive());

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findUser(id));
    }

    /* ---------- Helper Methods ---------- */

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(env.getProperty("error.user.not.found")));
    }
}
