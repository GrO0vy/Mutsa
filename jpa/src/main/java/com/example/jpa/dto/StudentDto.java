package com.example.jpa.dto;

import com.example.jpa.entities.StudentEntity;
import lombok.Data;

@Data
public class StudentDto {
    private Long id; //Entity.id
    private String name; // Entitiy.name
    private String email; // Entity.email

    //정적 팩토리 메소드 패턴
    public static StudentDto fromEntity(StudentEntity studentEntity){
        StudentDto dto = new StudentDto();
        dto.setId(studentEntity.getId());
        dto.setName(studentEntity.getName());
        dto.setName(studentEntity.getEmail());
        return dto;
    }
}
