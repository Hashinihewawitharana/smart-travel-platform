package com.smarttravel.userservice.service;


import com.smarttravel.userservice.dto.UserDTO;
import com.smarttravel.userservice.entity.User;
import com.smarttravel.userservice.exception.UserNotFoundException;
import com.smarttravel.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Ensure requestSeats is an int (or Integer) in DTO
        User user = new User(
                null,                     // id
                userDTO.getName(),         // name
                userDTO.getEmail(),        // email
                userDTO.getPassword(),     // password
                userDTO.getRequestSeats(),
                userDTO.getRequestRooms()
        );

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRequestSeats(),
                savedUser.getRequestRooms()
        );
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRequestSeats(),
                user.getRequestRooms()
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRequestSeats(),
                        user.getRequestRooms()
                ))
                .collect(Collectors.toList());
    }
}
