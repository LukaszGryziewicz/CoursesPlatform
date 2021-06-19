package com.coursesPlatform.lecture;

import com.coursesPlatform.course.Course;
import com.coursesPlatform.course.CourseRepository;
import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;

    public LectureService(LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }

    public Lecture add(Lecture lecture, String courseTitle) {
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

    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

}
