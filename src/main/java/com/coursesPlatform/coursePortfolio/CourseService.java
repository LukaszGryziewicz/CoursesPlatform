package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    Course add(Course course, String categoryTitle) {
        Optional<Category> categoryByTitle = categoryRepository.findCategoryByTitle(categoryTitle);
        categoryByTitle.orElseThrow(CategoryNotFoundException::new);

        Optional<Course> titleAndDescription = courseRepository.findCourseByTitleAndDescription(course.getTitle(), course.getDescription());
        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Course with given title and description already exists");
        }
        if ( course.getDescription().length() >= 300 ) {
            throw new IllegalLengthException();
        }

        categoryByTitle.get().getCourses().add(course);
        return courseRepository.save(course);
    }

    List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
