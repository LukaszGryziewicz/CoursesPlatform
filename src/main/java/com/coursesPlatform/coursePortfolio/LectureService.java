package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;

    LectureService(LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }

    Lecture add(Lecture lecture, String courseTitle) {
        Optional<Course> courseByTitle = courseRepository.findCourseByTitle(courseTitle);
        courseByTitle.orElseThrow(CourseNotFoundException::new);

        Optional<Lecture> titleAndDescription = lectureRepository.findLectureByTitleAndDescription(lecture.getTitle(), lecture.getDescription());
        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Lecture with given title and description already exists");
        }
        if ( lecture.getDescription().length() >= 300 ) {
            throw new IllegalLengthException();
        }

        courseByTitle.get().getLectures().add(lecture);
        return lectureRepository.save(lecture);
    }

    List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }
}
