package com.coursesPlatform.coursePortfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:4200/")
class CourseController {
    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCourses = courseService.findAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping("/add/{title}")
    ResponseEntity<Course> addNewCourse(@RequestBody Course course, @PathVariable("title") String categoryTitle) {
        Course add = courseService.add(course, categoryTitle);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

}
