package com.example.yaothao.schoolmanagement.service.impl;

import com.example.yaothao.schoolmanagement.entity.Teacher_Student;
import com.example.yaothao.schoolmanagement.repository.TeacherStudentRepository;
import com.example.yaothao.schoolmanagement.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {
    private final TeacherStudentRepository repository;

    @Autowired
    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository) {
        this.repository = teacherStudentRepository;
    }

    @Override
    public List<Teacher_Student> getAllTeacherStudent() {
        return repository.findAll();
    }

    @Override
    public void save(Teacher_Student ts) {
        repository.save(ts);
    }
}
