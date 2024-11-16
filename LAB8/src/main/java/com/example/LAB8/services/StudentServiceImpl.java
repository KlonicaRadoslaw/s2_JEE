/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB8.services;

import com.example.LAB8.converters.StudentConverter;
import com.example.LAB8.converters.StudentMapper;
import com.example.LAB8.domain.Student;
import com.example.LAB8.dtos.StudentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.LAB8.repositories.StudentRepository;

/**
 *
 * @author student
 */
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    //private final StudentConverter studentConverter;
    private final StudentMapper studentMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .toList();
    }
    
    @Override
    public List<StudentDto> getAllStudentsNoAttachments() {
        return studentRepository.findAllNoAttachment();
    }
}
