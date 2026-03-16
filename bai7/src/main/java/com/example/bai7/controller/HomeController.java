package com.example.bai7.controller;

import com.example.bai7.entity.Course;
import com.example.bai7.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final CourseService courseService;

    public HomeController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        Page<Course> coursePage = courseService.listCourses(page, keyword);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("title", "Home");
        return "home";
    }

    @GetMapping("/courses")
    public String coursesRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword) {
        return "redirect:/home?keyword=" + keyword;
    }
}

