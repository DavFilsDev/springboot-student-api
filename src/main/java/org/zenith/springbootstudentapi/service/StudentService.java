package org.zenith.springbootstudentapi.service;

import org.springframework.stereotype.Service;
import org.zenith.springbootstudentapi.model.Student;
import org.zenith.springbootstudentapi.repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> addStudents(List<Student> newStudents) {
        studentRepository.saveAll(newStudents);
        return studentRepository.findAll();
    }

    public String welcomeMessage(String name) {
        return "Welcome " + name;
    }

    public String getStudentsNames() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(Student::getFirstName)
                .collect(Collectors.joining(", "));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
