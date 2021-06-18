package com.coursesPlatform.course;

import com.coursesPlatform.category.IllegalLengthException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course add(Course course) {
        Optional<Course> titleAndDescription = courseRepository.findCourseByTitleAndDescription(course.getTitle(), course.getDescription());
        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Course with given title and description already exists");
        }
        if ( course.getDescription().length() >= 300 ) {
            throw new IllegalLengthException();
        }

        return courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
