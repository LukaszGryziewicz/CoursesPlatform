package com.coursesPlatform.category;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
   private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    void add(Category category){
       Optional<Category> categoryById = categoryRepository.findCategoryById(category.getId());

       if ( categoryById.isPresent() ){
           throw  new IllegalStateException("Category already exists");
       }

        categoryRepository.save(category);
    }
}
