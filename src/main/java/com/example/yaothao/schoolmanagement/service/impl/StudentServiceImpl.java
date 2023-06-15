package com.example.yaothao.schoolmanagement.service.impl;

import com.example.yaothao.schoolmanagement.DTO.StudentDTO;
import com.example.yaothao.schoolmanagement.entity.Student;
import com.example.yaothao.schoolmanagement.entity.Teacher;
import com.example.yaothao.schoolmanagement.entity.Teacher_Student;
import com.example.yaothao.schoolmanagement.repository.StudentRepository;
import com.example.yaothao.schoolmanagement.service.StudentService;
import com.example.yaothao.schoolmanagement.service.TeacherService;
import com.example.yaothao.schoolmanagement.service.TeacherStudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final TeacherService teacherService;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, TeacherService teacherService) {

        this.repository = repository;
        this.teacherService = teacherService;
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
    public Student save(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        List<Teacher_Student> teacherStudentList = new ArrayList<>();
        for (String teacherName : studentDTO.getTeachers()) {
            Teacher teacher = teacherService.findByName(teacherName);
            if (teacher == null) {
                teacher = new Teacher();
                teacher.setName(teacherName);
                teacher.setTeacher_students(new ArrayList<>());
                teacherService.save(teacher);

            }
            Teacher_Student ts = new Teacher_Student();
            ts.setStu(student);
            ts.setTeacher(teacher);
            teacherStudentList.add(ts);
        }
        student.setTeacher_students(teacherStudentList);
        repository.save(student);

        return student;
    }

    @Override
    @Transactional
    public void delete(Student student) {
        repository.delete(student);
    }


}
