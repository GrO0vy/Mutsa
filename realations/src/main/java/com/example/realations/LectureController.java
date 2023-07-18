package com.example.realations;

import com.example.realations.entity.InstructorEntity;
import com.example.realations.entity.LectureEntity;
import com.example.realations.repo.InstructorRepository;
import com.example.realations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("lectures")
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사 배정
    @PutMapping("{id}/instructor/{instructorId}")
    // 응답 바디가 없을 것이다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLectureInstructor(
            @PathVariable("id") Long id,
            @PathVariable("instructorId") Long instructorId
    )
    {
        Optional<LectureEntity> optionalLecture
            = lectureRepository.findById(id);

        if(optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<InstructorEntity> optionalInstructor
                = instructorRepository.findById(instructorId);

        if(optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        LectureEntity lecture = optionalLecture.get();
        InstructorEntity instructor = optionalInstructor.get();

        // Java 객체 쓰듯이 LectureEntity 의 instructor 필드에 갑 삽입
        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }
}
