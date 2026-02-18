package com.example.question2_student_api.Controller;

import com.example.question2_student_api.model.Student;
import com.example.question2_student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getByMajor(@PathVariable String major) {
        return ResponseEntity.ok(studentRepository.findByMajorIgnoreCase(major));
    }

    // GET students filtered by minimum GPA
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {
        return ResponseEntity.ok(studentRepository.findByGpaGreaterThanEqual(gpa));
    }

    // POST register new student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT update student
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Optional<Student> existing = studentRepository.findById(studentId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Student student = existing.get();
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setMajor(updatedStudent.getMajor());
        student.setGpa(updatedStudent.getGpa());

        return ResponseEntity.ok(studentRepository.save(student));
    }

    // DELETE student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(studentId);
        return ResponseEntity.noContent().build();
    }
}


