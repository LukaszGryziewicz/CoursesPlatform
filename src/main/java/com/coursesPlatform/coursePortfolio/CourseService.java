package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    @Value("${title.length.limit}")
    private int titleLengthLimit;
    @Value("${description.length.limit}")
    private int descriptionLengthLimit;


    CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    Course add(Course course, String categoryTitle) {
        Optional<Category> categoryByTitle = categoryRepository.findCategoryByTitle(categoryTitle);
        Category category = categoryByTitle.orElseThrow(CategoryNotFoundException::new);

        Optional<Course> titleAndDescription = courseRepository.findCourseByTitleAndDescription(course.getTitle(), course.getDescription());
        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Course with given title and description already exists");
        }
        if ( course.getDescription().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( course.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }

        category.getCourses().add(course);
        return courseRepository.save(course);
    }

    List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
