package com.example.question2_student_api.repository;

import com.example.question2_student_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Get students by major
    List<Student> findByMajorIgnoreCase(String major);

    // Get students with GPA >= minGpa
    List<Student> findByGpaGreaterThanEqual(Double gpa);
}
