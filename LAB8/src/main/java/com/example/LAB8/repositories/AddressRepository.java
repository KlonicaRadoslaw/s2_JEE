/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB8.repositories;

import com.example.LAB8.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author student
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
