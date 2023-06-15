package com.example.yaothao.schoolmanagement.service;

import com.example.yaothao.schoolmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentByID(Integer id);

    void save(Student student);

    void delete(Student student);

}
