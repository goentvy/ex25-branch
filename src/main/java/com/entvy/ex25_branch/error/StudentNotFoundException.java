package com.entvy.ex25_branch.error;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id) {
        super("학생을 찾을 수 없습니다. ID: " + id);
    }
}
