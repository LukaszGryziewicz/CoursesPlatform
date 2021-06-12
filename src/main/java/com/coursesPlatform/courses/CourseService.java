package com.coursesPlatform.courses;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    private final InMemoryCourseRepository courseRepository;

    public CourseService(InMemoryCourseRepository inMemoryRepository) {
        this.courseRepository = inMemoryRepository;
    }


    public void addNewCourse(Course course) {
    courseRepository.add(course);

    }

   public List<Course> getAllCourses() {
     return courseRepository.getCoursesList();
    }

}
