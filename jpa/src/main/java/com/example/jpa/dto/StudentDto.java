package com.example.jpa.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id; //Entity.id
    private String name; // Entitiy.name
    private String email; // Entity.email
}
