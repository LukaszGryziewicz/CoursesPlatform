package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByTitleAndDescription(String title, String description);

    Optional<Category> findCategoryByTitle(String title);
}
