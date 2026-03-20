package org.zenith.springbootstudentapi.controller;

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
    public String welcome(@RequestParam String name) {
        return studentService.welcomeMessage(name);
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