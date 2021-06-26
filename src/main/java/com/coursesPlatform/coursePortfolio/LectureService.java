package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;
    @Value("${title.length.limit}")
    private int titleLengthLimit;
    @Value("${description.length.limit}")
    private int descriptionLengthLimit;

    LectureService(LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }

    Lecture add(Lecture lecture, String courseTitle) {
        Optional<Course> courseByTitle = courseRepository.findCourseByTitle(courseTitle);
        Course course = courseByTitle.orElseThrow(CourseNotFoundException::new);

        Optional<Lecture> titleAndDescription = lectureRepository.findLectureByTitleAndDescription(lecture.getTitle(), lecture.getDescription());
        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Lecture with given title and description already exists");
        }
        if ( lecture.getDescription().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( lecture.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }

        course.getLectures().add(lecture);
        return lectureRepository.save(lecture);
    }

    List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }
}
