package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.CategoryTitleAlreadyExists;
import com.coursesPlatform.exceptions.IllegalDescriptionLengthException;
import com.coursesPlatform.exceptions.IllegalTitleLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
class CategoryService {
    private final CategoryRepository categoryRepository;
    @Value("${title.length.limit}")
    private int titleLengthLimit;
    @Value("${description.length.limit}")
    private int descriptionLengthLimit;


    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    CategoryDTO convertCategoryToDTO(Category category) {
        return new CategoryDTO(
                category.getTitle(),
                category.getDescription()
        );
    }

    Category convertDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    private CourseDTO convertCourseToDTO(Course course) {
        return new CourseDTO(
                course.getTitle(),
                course.getDescription()
        );
    }

    List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertCategoryToDTO)
                .collect(toList());
    }

    CategoryDTO add(CategoryDTO categoryDTO) {

        Optional<Category> categoryTitle = categoryRepository
                .findCategoryByTitle(categoryDTO.getTitle());

        if ( categoryTitle.isPresent() ) {
            throw new CategoryTitleAlreadyExists();
        }
        if ( categoryDTO.getTitle().length() >= titleLengthLimit ) {
            throw new IllegalTitleLengthException();
        }
        if ( categoryDTO.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalDescriptionLengthException();
        }

        Category category = categoryRepository.save(convertDTOToCategory(categoryDTO));
        return convertCategoryToDTO(category);
    }

    List<CourseDTO> findCategoriesOfCourse(String title) {
        return categoryRepository.findCategoryByTitle(title)
                .map(this::mapToDtos)
                .orElseThrow(CategoryNotFoundException::new);
    }

    private List<CourseDTO> mapToDtos(Category category) {
        return category.getCourses()
                .stream()
                .map(this::convertCourseToDTO)
                .collect(toList());
    }
}
