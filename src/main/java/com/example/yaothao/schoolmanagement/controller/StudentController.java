package com.example.yaothao.schoolmanagement.controller;

import com.example.yaothao.schoolmanagement.DTO.StudentDTO;
import com.example.yaothao.schoolmanagement.VO.StudentVO;
import com.example.yaothao.schoolmanagement.VO.TeacherVO;
import com.example.yaothao.schoolmanagement.entity.Student;
import com.example.yaothao.schoolmanagement.entity.Teacher;
import com.example.yaothao.schoolmanagement.entity.Teacher_Student;
import com.example.yaothao.schoolmanagement.service.StudentService;
import com.example.yaothao.schoolmanagement.service.TeacherService;
import com.example.yaothao.schoolmanagement.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final TeacherService teacherService;

    private final TeacherStudentService teacherStudentService;

    @Autowired
    public StudentController(StudentService studentService, TeacherService teacherService,
                             TeacherStudentService teacherStudentService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherStudentService = teacherStudentService;
    }

    @GetMapping("/hello")
    public void getHello() {
        System.out.println("hello received");
    }

    @GetMapping("/students")
    public List<StudentVO> getAllStudents() {

        List<Student> studentList = studentService.getAllStudents();
        List<StudentVO> studentVOList = new ArrayList<>();

        for (Student s : studentList) {
            StudentVO studentVO = new StudentVO();
            studentVO.setId(s.getId());
            studentVO.setName(s.getName());
            studentVOList.add(studentVO);
            List<TeacherVO> teacherVOList = new ArrayList<>();
            for (Teacher_Student ts : s.getTeacher_students()) {
                TeacherVO teacherVO = new TeacherVO(ts.getTeacher().getId(), ts.getTeacher().getName());
                teacherVOList.add(teacherVO);
            }
            studentVO.setTeacherVOList(teacherVOList);
        }
        return studentVOList;
    }

    @GetMapping("/student/{id}")
    public StudentVO getStudentByID(@PathVariable Integer id) {
        Student student = studentService.getStudentByID(id);
        StudentVO studentVO = new StudentVO();
        studentVO.setId(student.getId());
        studentVO.setName(student.getName());
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (Teacher_Student ts : student.getTeacher_students()) {
            TeacherVO teacherVO = new TeacherVO(ts.getTeacher().getId(), ts.getTeacher().getName());
            teacherVOList.add(teacherVO);
        }
        studentVO.setTeacherVOList(teacherVOList);
        return studentVO;
    }

    @PostMapping("/new-student")
    public StudentVO addNewStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        List<Teacher_Student> teacherStudentList = new ArrayList<>();
        for (String teacherName : studentDTO.getTeachers()) {
            Teacher teacher = teacherService.findByName(teacherName);
            if (teacher == null) {
//                System.out.println("teacher is null" + teacherInfo.getValue());
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
        studentService.save(student);

        StudentVO studentVO = new StudentVO();
        studentVO.setId(student.getId());
        studentVO.setName(student.getName());
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (Teacher_Student ts : teacherStudentList) {
            TeacherVO teacherVO = new TeacherVO(ts.getTeacher().getId(), ts.getTeacher().getName());
            teacherVOList.add(teacherVO);
        }
        studentVO.setTeacherVOList(teacherVOList);
        return studentVO;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        Student student = studentService.getStudentByID(id);

        if (student != null) {
            studentService.delete(student);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }
}
