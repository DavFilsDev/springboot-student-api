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
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> newStudents) {
        try {
            List<Student> allStudents = studentService.addStudents(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(allStudents);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        try {
            if (acceptHeader == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Accept header is required");
            }

            if ("text/plain".equals(acceptHeader) || "application/json".equals(acceptHeader)) {
                String studentNames = studentService.getStudentsNames();
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", acceptHeader)
                        .body(studentNames);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format non supporté.");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request");
        }
    }
}