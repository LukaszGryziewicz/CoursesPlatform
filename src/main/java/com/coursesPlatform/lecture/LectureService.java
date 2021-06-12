package com.coursesPlatform.lecture;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture add(Lecture lecture) {
        Optional<Lecture> lectureById = lectureRepository.findLectureById(lecture.getId());

        if ( lectureById.isPresent() ) {
            throw new IllegalStateException("Lecture already exists");
        }
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

}
