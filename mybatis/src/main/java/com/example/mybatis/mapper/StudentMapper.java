package com.example.mybatis.mapper;

import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // MyBatis가 Mapper가 붙은 클래스를 DB 통신에 사용할 준비
public interface StudentMapper {
    // INSERT INTO students (name, age, phone, email)
    // VALUE (?, ?, ?, ?);
    @Insert("INSERT INTO students (name, age, phone, email)"+
            "VALUES (#{name}, #{age}, #{phone}, #{email})")
    void insertStudent(Student student);

    // SELECT * FROM students;
    // 복수 개의 Students를 반환할 return 타입 -> List<Student>

    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student selectStudent(Long id);

    @Update("UPDATE students SET" +
            "name = #{name}," +
            "age = #{age}" +
            "phone = #{phone}," +
            "email = #{email}" +
            "WHERE id = #{id}")
    void updateStudent(Student student);

    @Delete("DELETE FROM students" +
            "WHERE id = #{id}")
    void deleteStudent(Long id);


    //DML : SELELCT, INSERT, UPDATE, DELETE
//    @Insert()
//    @Update()
//    @Delete()
}
