/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 *
 * @author student
 */

@Data
public class UserDTO {
    Long id;

    @NotBlank
    String role;

    @NotBlank
    String username;

    @NotBlank
    String fullName;

    String password;

    Boolean enabled = false;
}

