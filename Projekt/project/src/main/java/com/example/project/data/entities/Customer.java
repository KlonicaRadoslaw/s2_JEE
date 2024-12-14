/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.data.entities;

/**
 *
 * @author student
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @NotBlank
    @Size(min=3, max=100)
    String firstName;

    @Column
    @NotBlank
    @Size(min=3, max=100)
    String lastName;

    @Column
    @Email
    String emailAddress;

    @Column
    String address;

    @Column
    String city;

    @Column
    String country;

    @Column
    String phoneNumber;
}
