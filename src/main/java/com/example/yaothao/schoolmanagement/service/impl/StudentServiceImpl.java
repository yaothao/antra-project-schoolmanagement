package com.example.yaothao.schoolmanagement.service.impl;

import com.example.yaothao.schoolmanagement.entity.Student;
import com.example.yaothao.schoolmanagement.repository.StudentRepository;
import com.example.yaothao.schoolmanagement.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentByID(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    @Transactional
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    @Transactional
    public void delete(Student student) {
        repository.delete(student);
    }


}
