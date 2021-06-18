package com.coursesPlatform.lecture;

import com.coursesPlatform.category.IllegalLengthException;
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
        Optional<Lecture> titleAndDescription = lectureRepository.findLectureByTitleAndDescription(lecture.getTitle(), lecture.getDescription());

            if ( titleAndDescription.isPresent() ){
            throw new IllegalStateException("Lecture with given title and description already exists");
        }
        if (lecture.getDescription().length()>=300){
            throw  new IllegalLengthException();
        }
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

}
