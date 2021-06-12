package com.coursesPlatform.category;

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
        Optional<Category> categoryById = categoryRepository.findCategoryById(category.getId());

        if ( categoryById.isPresent() ) {
            throw new IllegalStateException("Category already exists");
        }
         if (category.getTitle().length() >= 300 ) {
             throw new IllegalLengthException();
         }



       return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
