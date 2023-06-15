package com.example.jpa;

import com.example.jpa.dto.StudentDto;
import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private final AppRepository repository;
    private final StudentRepository studentRepository;

    public AppService(AppRepository repository, StudentRepository studentRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // CREATE
    public StudentEntity createStudent(String name, Integer age, String phone, String email){
       // 새로운 Student를 만들고 싶다.
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);

        // 저장
        // save() 메소드는 생성된 엔티티를 반환한다.
        newEntity = this.studentRepository.save(newEntity);
        return newEntity;
    }

    // READ ALL
    public List<StudentDto> readStudentAll(){
        System.out.println(this.studentRepository.findAll());
        List<StudentEntity> studentEntityList = this.studentRepository.findAll();

        List<StudentDto> studentDtoList = new ArrayList<>();
        for(StudentEntity studentEntity : this.studentRepository.findAll()){
//            StudentDto studentDto = new StudentDto();
//            studentDto.setId(studentEntity.getId());
//            studentDto.setName(studentEntity.getName());
//            studentDto.setEmail(studentEntity.getEmail());
//            studentDtoList.add(studentDto);
            studentDtoList.add(StudentDto.fromEntity(studentEntity));
        }
        //return studentEntityList;
        return studentDtoList;
    }

    // READ
    public void readStudent(Long id){
        // Optional 을 이용해 null 처리
        Optional<StudentEntity> optionalStudentEntity = this.studentRepository.findById(id);
        // 1. 실제 데이터가 온 경우
        if(optionalStudentEntity.isPresent()){
            System.out.println(optionalStudentEntity.get());
        }
        // 2. 결과가 null 이 되었을 경우
        else{
            System.out.println("no result");
        }
//        System.out.println(this.studentRepository.findById(id));
    }

    //UPDATE
    public void updateStudent(Long id, String name){
        Optional<StudentEntity> optionalEntity = this.studentRepository.findById(id);

        if(optionalEntity.isPresent()){
            StudentEntity student = optionalEntity.get();
            student.setName(name);
            this.studentRepository.save(student);
        }
        else{
            System.out.println("could not find");
        }
    }

    // DELETE
    public void deleteStudent(Long id){
        Optional<StudentEntity> optionalEntity =
                this.studentRepository.findById(id);

        if(optionalEntity.isPresent()){
            StudentEntity student = optionalEntity.get();
            this.studentRepository.delete(student);
        }
        else{
            System.out.println("could not find");
        }
    }

    public void findAllByTest(){
        List<StudentEntity> studentEntities =
                this.studentRepository.findAllByOrderByName();
        for(int i = 0; i < 5; i++){
            System.out.println(studentEntities.get(i));
        }

        studentEntities = this.studentRepository.findAllByOrderByAgeDesc();
        for (int i = 0 ; i < 6; i++){
            System.out.println(studentEntities.get(i));
        }

        studentEntities = this.studentRepository.findAllByAgeLessThan(21);
        for(int i = 0; i < 5; i++){
            System.out.println(studentEntities.get(i));
        }

        studentEntities = this.studentRepository.findAllByPhoneLike("010-%");
        for(int i = 0; i < 5; i++){
            System.out.println(studentEntities.get(i));
        }
    }


}
