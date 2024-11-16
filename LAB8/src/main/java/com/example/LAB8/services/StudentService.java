/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB8.services;

import com.example.LAB8.domain.Student;
import com.example.LAB8.dtos.StudentDto;
import java.util.List;

/**
 *
 * @author student
 */
public interface StudentService {
    public List<StudentDto> getAllStudents();
    public List<StudentDto> getAllStudentsNoAttachments();
}
