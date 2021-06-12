package com.coursesPlatform.course;

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
        Optional<Course> courseById = courseRepository.findCourseById(course.getId());
        if ( courseById.isPresent() ) {
            throw new IllegalStateException("course already exists");
        }

        return courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
