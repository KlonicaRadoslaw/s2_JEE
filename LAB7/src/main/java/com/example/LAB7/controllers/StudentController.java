/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB7.controllers;

import com.example.LAB7.entities.Student;
import com.example.LAB7.services.StudentService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author student
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getStudentList();
    }
    
    @PostMapping("/student/add")
    public String add(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return "Dodano studenta do bazy";
    }
    
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable int id){
        if (!studentService.studentExists(id)){
            throw new ResourceNotFoundException();
        }
        studentService.deleteStudent(id);
    }

    @PutMapping("/student/{id}")
    public void update(@PathVariable int id, @RequestBody @Valid Student student){
        if (!studentService.studentExists(id)){
            throw new ResourceNotFoundException();
        }
        studentService.updateStudent(id, student);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleException(MethodArgumentNotValidException ex){
        List<String> errorList = ex.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                    .toList();
        Map<String, List<String>> response = new HashMap<>();
        response.put("errors", errorList);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
