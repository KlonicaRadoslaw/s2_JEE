/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB10.repository;

import com.example.LAB10.model.UserDao;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author student
 */
public interface UserRepository extends CrudRepository<UserDao, Integer> {

    UserDao findByUsername(String username);
}
