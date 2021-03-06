package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.*;
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

        Optional<Course> courseTitle = courseRepository
                .findCourseByTitle(courseDTO.getTitle());
        if ( courseTitle.isPresent() ) {
            throw new CourseTitleAlreadyExists();
        }
        if (courseDTO.getTitle().length() >= titleLengthLimit) {
            throw new IllegalTitleLengthException();
        }
        if (courseDTO.getDescription().length() >= descriptionLengthLimit) {
            throw new IllegalDescriptionLengthException();
        }

        Course savedCourse = courseRepository.save(convertDTOToCourse(courseDTO));
        category.add(savedCourse);
        categoryRepository.save(category);
        return convertCourseToDTO(savedCourse);
    }

    List<CourseDTO> findAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertCourseToDTO)
                .collect(Collectors.toList());
    }

    List<LectureDTO> findLecturesOfCourse(String title) {
        return courseRepository.findCourseByTitle(title)
                .map(this::mapToDtos)
                .orElseThrow(CourseNotFoundException::new);
    }

    private List<LectureDTO> mapToDtos(Course course) {
        return course.getLectures()
                .stream()
                .map(this::convertLectureToDTO)
                .collect(Collectors.toList());
    }

    private LectureDTO convertLectureToDTO(Lecture lecture) {
        return new LectureDTO(
                lecture.getTitle(),
                lecture.getDescription(),
                lecture.getPrice(),
                lecture.getDuration()
        );
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
