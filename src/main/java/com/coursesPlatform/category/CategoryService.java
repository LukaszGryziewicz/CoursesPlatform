package com.coursesPlatform.category;

import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category add(Category category) {
        Optional<Category> titleAndDescription = categoryRepository.findCategoryByTitleAndDescription(category.getTitle(), category.getDescription());

        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Category with given title and description already exists");
        }
        if ( category.getTitle().length() >= 300 ) {
            throw new IllegalLengthException();
        }


        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
