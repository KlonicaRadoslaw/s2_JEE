/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.data.repositories;

import com.example.project.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author student
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>,
    PagingAndSortingRepository<Customer, Long>,
    JpaSpecificationExecutor<Customer> {
}
