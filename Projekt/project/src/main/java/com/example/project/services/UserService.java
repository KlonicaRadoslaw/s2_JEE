/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.services;
import com.example.project.data.entities.User;
import com.example.project.data.repositories.UserRepository;
import com.example.project.data.specifications.UserDatatableFilter;
import com.example.project.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author student
 */


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<User> getUsersForDatatable(String queryString, Pageable pageable) {

        UserDatatableFilter userDatatableFilter = new UserDatatableFilter(queryString);

        return userRepository.findAll(userDatatableFilter, pageable);
    }

    public User createNewUser(UserDTO userDTO) {
        User user = User.builder()
            .fullName(userDTO.getFullName())
            .role(userDTO.getRole())
            .username(userDTO.getUsername())
            .password(passwordEncoder.encode(userDTO.getPassword()))
            .enabled(true)
            .build();

        return userRepository.save(user);
    }

    public User updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        user.setFullName(userDTO.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setEnabled(userDTO.getEnabled());
        user.setRole(userDTO.getRole());

        return userRepository.save(user);
    }
}

