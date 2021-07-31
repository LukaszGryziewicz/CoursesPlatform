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
    ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> allCourses = categoryService.findAllCategories();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<CategoryDTO> addNewCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO add = categoryService.add(categoryDTO);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @GetMapping("/courses/{categoryTitle}")
    ResponseEntity<List<CourseDTO>> getAllCoursesOfCategory(@PathVariable("categoryTitle") String categoryTitle) {
        List<CourseDTO> categoriesOfCourse = categoryService.findCategoriesOfCourse(categoryTitle);
        return new ResponseEntity<>(categoriesOfCourse, HttpStatus.OK);
    }


}
