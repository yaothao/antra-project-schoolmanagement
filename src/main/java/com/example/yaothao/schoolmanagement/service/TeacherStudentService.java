package com.example.yaothao.schoolmanagement.service;

import com.example.yaothao.schoolmanagement.entity.Teacher_Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherStudentService {

    List<Teacher_Student> getAllTeacherStudent();

    void save(Teacher_Student ts);
}
