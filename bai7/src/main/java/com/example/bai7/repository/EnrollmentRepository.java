package com.example.bai7.repository;

import com.example.bai7.entity.Enrollment;
import com.example.bai7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}

