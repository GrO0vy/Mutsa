package com.example.realations;

import com.example.realations.entity.LectureEntity;
import com.example.realations.entity.StudentEntity;
import com.example.realations.repo.LectureRepository;
import com.example.realations.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @PutMapping("{id}/lectures/{lectureId}")
    public void updateStudentLectures(
            @PathVariable("id") Long id,
            @PathVariable("lectureId") Long lectureId
    ){
        Optional<StudentEntity> optionalStudent
                = studentRepository.findById(id);

        if(optionalStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<LectureEntity> optionalLecture
                = lectureRepository.findById(lectureId);

        if(optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        StudentEntity student = optionalStudent.get();
        LectureEntity lecture = optionalLecture.get();

        student.getAttending().add(lecture);
        studentRepository.save(student);
    }
}
