package com.coursesPlatform.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  Optional <Category> findCategoryById(Long id);

  Optional<Category>findCategoryByTitleAndDescription(String title,String description);
}
