package com.guney.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guney.springdemo.entity.Student;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data only once
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Samet", "Guney"));
        theStudents.add(new Student("test", "TEST"));
        theStudents.add(new Student("QWERTY", "ASDFGH"));
        theStudents.add(new Student("123456", "789012"));
        theStudents.add(new Student("/*-+", "{[()]}"));
    }

    // define endpoint for "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return a student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }
}
