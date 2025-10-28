package com.entvy.ex25_branch.controller;

import com.entvy.ex25_branch.model.Student;
import com.entvy.ex25_branch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

//  http://localhost:8080/students
//  학생 전체 조회
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
//  http://localhost:8080/students
//  학생 추가
    @PostMapping
    public String create(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/students";
    }
//  http://localhost:8080/students/1/edit
//  수정폼
    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "student/form";
    }
//  http://localhost:8080/students/1
//  수정처리
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }
//  http://localhost:8080/students/1/delete
//  삭제처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
