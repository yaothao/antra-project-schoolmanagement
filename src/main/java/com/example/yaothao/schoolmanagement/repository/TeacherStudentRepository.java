package com.example.yaothao.schoolmanagement.repository;

import com.example.yaothao.schoolmanagement.entity.Teacher_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentRepository extends JpaRepository<Teacher_Student, Integer> {
}
