package com.entvy.ex25_branch.controller;

import com.entvy.ex25_branch.model.Student;
import com.entvy.ex25_branch.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students/valid")
@RequiredArgsConstructor
public class StudentController_valid {

    private final StudentService studentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.getAllStudents());

        return "student/list_validtest";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form_validtest";
    }

    @GetMapping("/new/valid")
    public String createFormValid(Model model) {
        model.addAttribute("student", new Student());
        return "student/form_validtest";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "student/form_validtest";
        }
        studentService.createStudent(student);
        return "redirect:/students/valid";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "student/form_validtest";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student/form_validtest";
        }
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students/valid";

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/valid";
    }
}
