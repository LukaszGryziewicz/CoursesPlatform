package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    CourseDTO add(CourseDTO courseDTO, String categoryTitle) {
        Optional<Category> categoryByTitle = categoryRepository.findCategoryByTitle(categoryTitle);
        Category category = categoryByTitle.orElseThrow(CategoryNotFoundException::new);

        Optional<Course> course = courseRepository
                .findCourseByTitleAndDescription(courseDTO.getTitle(), courseDTO.getDescription());
        if ( course.isPresent() ) {
            throw new IllegalStateException("Course with given title and description already exists");
        }
        if ( courseDTO.getDescription().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( courseDTO.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }

        Course savedCourse = courseRepository.save(convertDTOToCourse(courseDTO));
        return convertCourseToDTO(savedCourse);
    }

    List<CourseDTO> findAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertCourseToDTO)
                .collect(Collectors.toList());
    }

    private CourseDTO convertCourseToDTO(Course course) {
        return new CourseDTO(
                course.getTitle(),
                course.getDescription()
        );
    }

    private Course convertDTOToCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        return course;

    }
}
