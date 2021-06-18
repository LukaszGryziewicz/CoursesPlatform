package com.coursesPlatform.category;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCourses = categoryService.findAllCategories();
        return  new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category){
        Category add = categoryService.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }


}
