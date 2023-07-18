package com.example.realations;

import com.example.realations.entity.InstructorEntity;
import com.example.realations.entity.LectureEntity;
import com.example.realations.repo.InstructorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorRepository instructorRepository;

    @GetMapping("{id}/lectures")
    public void readInstructorLectures(
            @PathVariable("id") Long id
    ){
        Optional<InstructorEntity> optionalInstructor
                = instructorRepository.findById(id);

        InstructorEntity instructor = optionalInstructor.get();

        for(LectureEntity lecture: instructor.getLectures()){
            log.info(lecture.getName());
        }
    }
}
