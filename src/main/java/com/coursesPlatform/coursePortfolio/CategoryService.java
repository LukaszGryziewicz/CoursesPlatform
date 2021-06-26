package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertCategoryToDTO)
                .collect(Collectors.toList());
    }

    CategoryDTO add(CategoryDTO categoryDTO) {

        Optional<Category> titleAndDescription = categoryRepository
                .findCategoryByTitleAndDescription(categoryDTO.getTitle(),
                        categoryDTO.getDescription());

        if ( titleAndDescription.isPresent() ) {
            throw new IllegalStateException("Category with given title and description already exists");
        }
        if ( categoryDTO.getTitle().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( categoryDTO.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }

        Category category = categoryRepository.save(convertDTOToCategory(categoryDTO));
        return convertCategoryToDTO(category);
    }
}
