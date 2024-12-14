/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.init;
import com.example.project.data.entities.User;
import com.example.project.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 *
 * @author student
 */

@RequiredArgsConstructor
@Component
public class Bootstrap {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    void appReady(ApplicationReadyEvent event) {
        if (! userRepository.findByUsername("admin").isPresent()) {
            User adminUser = User.builder()
                .username("admin")
                .fullName("Admin Admin")
                .password(passwordEncoder.encode("admin123"))
                .role("ROLE_ADMIN")
                .enabled(true)
                .build();

            userRepository.save(adminUser);
        }

        if (! userRepository.findByUsername("user").isPresent()) {
            User userUser = User.builder()
                .username("user")
                .fullName("User User")
                .password(passwordEncoder.encode("user123"))
                .role("ROLE_USER")
                .enabled(true)
                .build();

            userRepository.save(userUser);
        }

        if (! userRepository.findByUsername("lowUser").isPresent()) {
            User lowUser = User.builder()
                .username("lowUser")
                .fullName("Low User")
                .password(passwordEncoder.encode("lowuser123"))
                .role("ROLE_READONLY_USER")
                .enabled(true)
                .build();

            userRepository.save(lowUser);
        }
    }
}

