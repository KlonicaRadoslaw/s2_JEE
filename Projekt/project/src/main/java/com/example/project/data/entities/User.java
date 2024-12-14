/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.data.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
/**
 *
 * @author student
 */


@Getter
@Setter
@Entity
@Table(name = "appuser")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String role;

    @NotBlank
    String username;

    @NotBlank
    String fullName;

    @Column(nullable = false, length = 64)
    String password;

    Boolean enabled = true;

    @CreationTimestamp
    private LocalDateTime dateCreated;

}

