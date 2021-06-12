package com.coursesPlatform.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final InMemoryCategoryRepository categoryRepository;

    public CategoryService(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addNewCategory(Category category) {
        categoryRepository.add(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getCategoryList();
    }
}
