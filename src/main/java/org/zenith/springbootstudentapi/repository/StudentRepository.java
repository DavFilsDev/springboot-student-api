package org.zenith.springbootstudentapi.repository;

import org.zenith.springbootstudentapi.model.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void saveAll(List<Student> newStudents) {
        students.addAll(newStudents);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
}
