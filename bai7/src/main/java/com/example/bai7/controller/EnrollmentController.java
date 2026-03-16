package com.example.bai7.controller;

import com.example.bai7.entity.Course;
import com.example.bai7.entity.Student;
import com.example.bai7.repository.StudentRepository;
import com.example.bai7.service.CourseService;
import com.example.bai7.service.EnrollmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StudentRepository studentRepository;

    public EnrollmentController(EnrollmentService enrollmentService,
                                CourseService courseService,
                                StudentRepository studentRepository) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId,
                         @AuthenticationPrincipal User user) {
        Course course = courseService.get(courseId);
        Student student = studentRepository.findByUsername(user.getUsername()).orElseThrow();
        enrollmentService.enroll(student, course);
        return "redirect:/my-courses";
    }

    @GetMapping("/my-courses")
    public String myCourses(@AuthenticationPrincipal User user, Model model) {
        Student student = studentRepository.findByUsername(user.getUsername()).orElseThrow();
        model.addAttribute("enrollments", enrollmentService.getByStudent(student));
        model.addAttribute("title", "My Courses");
        return "my-courses";
    }
}

