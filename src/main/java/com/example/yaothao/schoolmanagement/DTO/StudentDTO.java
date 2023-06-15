package com.example.yaothao.schoolmanagement.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StudentDTO {

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "teachers name can not be empty")
    private List<String> teachers;
}
