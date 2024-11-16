/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB8.repositories;

import com.example.LAB8.domain.Student;
import com.example.LAB8.dtos.StudentDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author student
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.LAB8.dtos.StudentDto(s.name, s.surname, s.age, "+
    "s.address.street, s.address.city, s.address.state, s.address.zip) from Student s")
    List<StudentDto> findAllNoAttachment();
}
