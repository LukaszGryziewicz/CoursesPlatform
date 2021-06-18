package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryService;
import com.coursesPlatform.course.Course;
import com.coursesPlatform.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CoursesPlatformApplication {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;

    @PostConstruct
    void init() {
        Category category = new Category("IT", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Category category2 = new Category("HR", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        Category category3 = new Category("SALES", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur");
        Category category4 = new Category("CLEANING", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        categoryService.add(category);
        categoryService.add(category2);
        categoryService.add(category3);
        categoryService.add(category4);

        Course course = new Course("Java", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Course course2 = new Course("JS", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        Course course3 = new Course("Python", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur");
        Course course4 = new Course("C#", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        courseService.add(course);
        courseService.add(course2);
        courseService.add(course3);
        courseService.add(course4);
    }

    public static void main(String[] args) {
        SpringApplication.run(CoursesPlatformApplication.class, args);
    }

}
