package com.example.bai7.controller;

import com.example.bai7.entity.Category;
import com.example.bai7.entity.Course;
import com.example.bai7.service.CategoryService;
import com.example.bai7.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/courses")
public class CourseAdminController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseAdminController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseService.listCourses(0, null).getContent());
        return "admin-courses-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryService.findAll());
        return "admin-courses-form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("course") Course course,
                         BindingResult result,
                         @RequestParam Long categoryId,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "admin-courses-form";
        }
        Category category = categoryService.get(categoryId);
        course.setCategory(category);
        courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseService.get(id);
        if (course == null) {
            return "redirect:/admin/courses";
        }
        model.addAttribute("course", course);
        model.addAttribute("categories", categoryService.findAll());
        return "admin-courses-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("course") Course course,
                       BindingResult result,
                       @RequestParam Long categoryId,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "admin-courses-form";
        }
        Category category = categoryService.get(categoryId);
        course.setId(id);
        course.setCategory(category);
        courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/admin/courses";
    }
}

