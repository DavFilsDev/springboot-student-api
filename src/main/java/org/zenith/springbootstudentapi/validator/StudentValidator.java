package org.zenith.springbootstudentapi.validator;

import org.springframework.stereotype.Component;
import org.zenith.springbootstudentapi.exception.BadRequestException;
import org.zenith.springbootstudentapi.model.Student;

import java.util.List;

@Component
public class StudentValidator {

    public void validate(List<Student> students) {
        for (Student student : students) {
            validateStudent(student);
        }
    }

    private void validateStudent(Student student) {
        if (student.getReference() == null || student.getReference().trim().isEmpty()) {
            throw new BadRequestException("Student reference is required");
        }

        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("Student first name is required");
        }

        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Student last name is required");
        }
    }
}
