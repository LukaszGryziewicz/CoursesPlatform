package com.coursesPlatform.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> allCourses = courseService.findAllCourses();
        return  new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course){
        Course add = courseService.add(course);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }


}
