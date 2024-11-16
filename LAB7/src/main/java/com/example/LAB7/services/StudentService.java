/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB7.services;

import com.example.LAB7.entities.Student;
import com.example.LAB7.entities.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author student
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }
    
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    
    public boolean deleteStudent(int id){
        if (!studentRepository.existsById(id)){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
    public boolean updateStudent(int id, Student student){
        if (!studentRepository.existsById(id)){
            return false;
        }
        student.setId(id);
        studentRepository.save(student);
        return true;
    }

    public boolean studentExists(int id){
        return studentRepository.existsById(id);
    }
}
