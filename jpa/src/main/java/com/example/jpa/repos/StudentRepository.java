package com.example.jpa.repos;

import com.example.jpa.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<내가 사용할 Entity, 그 Entity 의 ID ( 기본키 )의 컬럼 타입>
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
