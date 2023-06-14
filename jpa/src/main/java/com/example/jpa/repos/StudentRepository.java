package com.example.jpa.repos;

import com.example.jpa.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<내가 사용할 Entity, 그 Entity 의 ID ( 기본키 )의 컬럼 타입>
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

        // JPA 쿼리 메서드
        // 메소드 이름을 우리가 조회하고싶은 조건을 붙여서 만든다
        // 하나 또는 많이

        // SELECT * FROM students ORDER BY name;
        List<StudentEntity> findAllByOrderByName();
        //SELECT * FROM students ORDER BY age DESC;
        List<StudentEntity> findAllByOrderByAgeDesc();
}
