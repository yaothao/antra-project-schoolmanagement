package com.example.yaothao.schoolmanagement.service;

import com.example.yaothao.schoolmanagement.DTO.StudentDTO;
import com.example.yaothao.schoolmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentByID(Integer id);

    Student save(StudentDTO studentDTO);

    void delete(Student student);

}
