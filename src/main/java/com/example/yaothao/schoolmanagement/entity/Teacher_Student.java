package com.example.yaothao.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "teacher_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher_Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Teacher teacher;

    @Override
    public String toString() {
        return "Teacher_Student{" +
                "id='" + id + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher_Student that = (Teacher_Student) o;
        return Objects.equals(id, that.id) && Objects.equals(stu, that.stu) && Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stu, teacher);
    }
}
