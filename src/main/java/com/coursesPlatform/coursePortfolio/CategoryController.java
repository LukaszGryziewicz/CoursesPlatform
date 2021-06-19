package com.coursesPlatform.coursePortfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200/")
class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCourses = categoryService.findAllCategories();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
        Category add = categoryService.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }


}