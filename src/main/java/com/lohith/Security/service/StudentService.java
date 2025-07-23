package com.lohith.Security.service;


import com.lohith.Security.model.Student;
import com.lohith.Security.repo.StudentRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }
}
