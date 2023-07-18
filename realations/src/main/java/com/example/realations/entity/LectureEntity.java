package com.example.realations.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String day;
    private Integer startTime;
    private Integer endTime;

    @ManyToOne
    // 외래키 필드의 경우 해당 어노테이션을 통해 열 속성(제약조건, 이름 등) 을 바꿀 수 있음
    //@JoinColumn
    private InstructorEntity instructor;

    @ManyToMany(mappedBy = "attending")
    private List<StudentEntity> students;
}
