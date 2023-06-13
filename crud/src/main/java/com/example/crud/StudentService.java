package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDTO를 담는 변수
    private final List<StudentDTO> students = new ArrayList<>();
    private Long nextId = 1L;

    public StudentService(){
        createStudent("alex","alex@gmail.com");
        createStudent("chad","chad@gmail.com");
        createStudent("brad","brad@gmail.com");
    }

    // 새로운 StudentDTO 를 생성하는 메소드
    public StudentDTO createStudent(String name, String email) {
        StudentDTO student = new StudentDTO(nextId++, name, email);
        students.add(student);
        return student;
    }

    public List<StudentDTO> readStudentAll(){
        return students;
    }

    // Service에서 단일 StudenetDto를 주는 메소드
    public StudentDTO readStudent(Long id){
        for(StudentDTO student : students){
            if(student.getId() == id) return student;
        }
        return null;
    }

    public StudentDTO updateStudent(Long id, String name, String email){
        for(StudentDTO student : students){
            if(id == student.getId()){
                student.setName(name);
                student.setEmail(email);
                return student;
            }
        }
        return null;
//        StudentDTO student = readStudent(id);
//        if(!student.equals(null)){
//            student.setName(name);
//            student.setEmail(email);
//            return student;
//        }
//        return null;
    }

    public boolean deleteStudent(Long id){
        StudentDTO deleteStudent = readStudent(id);
        boolean isDeleted = false;
        for(StudentDTO student : students){
            if(student.equals(deleteStudent)) {
                students.remove(student);
                isDeleted =true;
                break;
            }
        }
        return isDeleted;
    }
}
