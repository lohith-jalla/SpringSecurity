package com.lohith.Security.repo;

import com.lohith.Security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByName(String name);
}
