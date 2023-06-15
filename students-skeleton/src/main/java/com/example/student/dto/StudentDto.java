package com.example.student.dto;

import com.example.student.entity.StudentEntity;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;

    // static factory method pattern
    public static StudentDto fromEntity(StudentEntity entity){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(entity.getId());
        studentDto.setName(entity.getName());
        studentDto.setAge(entity.getAge());
        studentDto.setPhone(entity.getPhone());
        studentDto.setEmail(entity.getEmail());
        return studentDto;
    }
}
