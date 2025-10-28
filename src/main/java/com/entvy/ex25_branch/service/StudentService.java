package com.entvy.ex25_branch.service;

import com.entvy.ex25_branch.error.DuplicateEmailException;
import com.entvy.ex25_branch.error.StudentNotFoundException;
import com.entvy.ex25_branch.mapper.StudentMapper;
import com.entvy.ex25_branch.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    public Student getStudent(Long id) {
        Student student = studentMapper.findById(id);
        if(student == null) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    public void createStudent(Student student) {
        if(studentMapper.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException(student.getEmail());
        }
        studentMapper.insert(student);
    }

    public void updateStudent(Student student) {
        studentMapper.update(student);
    }

    public void deleteStudent(Long id) {
        studentMapper.delete(id);
    }
}
