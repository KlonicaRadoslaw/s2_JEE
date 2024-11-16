/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB8.controllers;

import com.example.LAB8.domain.Student;
import com.example.LAB8.dtos.StudentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.LAB8.services.StudentServiceImpl;

/**
 *
 * @author student
 */
@RequiredArgsConstructor
@RequestMapping(value = "/students")
@RestController
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }
}
