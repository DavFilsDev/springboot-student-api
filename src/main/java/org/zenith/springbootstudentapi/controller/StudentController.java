package org.zenith.springbootstudentapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zenith.springbootstudentapi.model.Student;
import org.zenith.springbootstudentapi.service.StudentService;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name parameter is required");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.welcomeMessage(name));
    }

    @PostMapping("/students")
    public String createStudents(@RequestBody List<Student> newStudents) {
        return studentService.addStudents(newStudents);
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader("Accept") String acceptHeader) {
        if ("text/plain".equals(acceptHeader)) {
            return studentService.getStudentsNames();
        } else {
            return "Format non supporté.";
        }
    }
}