package com.example.yaothao.schoolmanagement.service;

import com.example.yaothao.schoolmanagement.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    Teacher findByName(String name);

    void save(Teacher teacher);
}
