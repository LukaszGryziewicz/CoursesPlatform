package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    Category add(Category category) {

        Optional<Category> titleAndDescription = categoryRepository.findCategoryByTitleAndDescription(category.getTitle(), category.getDescription());

        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Category with given title and description already exists");
        }
        if ( category.getTitle().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( category.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }

        return categoryRepository.save(category);
    }

    List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
