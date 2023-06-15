package com.example.yaothao.schoolmanagement.service.impl;

import com.example.yaothao.schoolmanagement.entity.Teacher;
import com.example.yaothao.schoolmanagement.repository.TeacherRepository;
import com.example.yaothao.schoolmanagement.service.TeacherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.repository = teacherRepository;
    }

    @Override
    public Teacher findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        repository.save(teacher);
    }
}
