package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
// @RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public StudentDto createStudent(StudentDto dto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(dto.getName());
        studentEntity.setAge(dto.getAge());
        studentEntity.setPhone(dto.getPhone());
        studentEntity.setEmail(dto.getEmail());
        StudentDto studentDto = StudentDto.fromEntity(this.studentRepository.save(studentEntity));

        return studentDto;
    }

    // READ
    public StudentDto readStudent(Long id) {
        Optional<StudentEntity> studentEntity = this.studentRepository.findById(id);
        if(studentEntity.isPresent()) return StudentDto.fromEntity(studentEntity.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // READ ALL
    public List<StudentDto> readStudentAll() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = this.studentRepository.findAll();

        // 1.
        for(var studentEntity : studentEntityList) {
            studentDtoList.add(StudentDto.fromEntity(studentEntity));
        }

        // 2. studentEntityList.forEach(entity -> studentDtoList.add(StudentDto.fromEntity(entity)));
        // 3. studentDtoList = studentRepository.findAll().stream().map(StudentDto::fromEntity).toList();

        return studentDtoList;
    }

    // UPDATE
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Optional<StudentEntity> studentEntity = this.studentRepository.findById(id);
        if(studentEntity.isPresent()){
            StudentEntity targetEntity = studentEntity.get();
            targetEntity.setName(dto.getName());
            targetEntity.setAge(dto.getAge());
            targetEntity.setPhone(dto.getPhone());
            targetEntity.setEmail(dto.getEmail());
            return StudentDto.fromEntity(this.studentRepository.save(targetEntity));
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE
    public void deleteStudent(Long id) {
        // 1. if(this.studentRepository.existById(id)) this.studentRepository.deleteById(id);

        // 2.
        Optional<StudentEntity> OptionalEntity = this.studentRepository.findById(id);
        if(OptionalEntity.isPresent()) {
            StudentEntity targetEntity = OptionalEntity.get();
            this.studentRepository.delete(targetEntity);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
