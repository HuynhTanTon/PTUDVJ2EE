package com.example.bai7.service;

import com.example.bai7.entity.Course;
import com.example.bai7.entity.Enrollment;
import com.example.bai7.entity.Student;
import com.example.bai7.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enroll(Student student, Course course) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            return;
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getByStudent(Student student) {
        return enrollmentRepository.findByStudent(student);
    }
}

