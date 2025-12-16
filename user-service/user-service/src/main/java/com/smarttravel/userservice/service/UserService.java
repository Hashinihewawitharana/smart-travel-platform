package com.smarttravel.userservice.service;
import com.smarttravel.userservice.dto.UserDTO;


import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
}


