package com.lohith.Security.controller;


import com.lohith.Security.model.Student;
import com.lohith.Security.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {

    private StudentService studentService;

    public GreetingController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String greeting() {
        return "Hello ";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user";
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        studentService.createStudent(student);

        return student;
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.getAll();
    }

}
