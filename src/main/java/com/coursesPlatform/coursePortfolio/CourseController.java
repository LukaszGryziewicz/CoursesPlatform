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
    ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> allCourses = courseService.findAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping("/add/{title}")
    ResponseEntity<CourseDTO> addNewCourse(@RequestBody CourseDTO courseDTO, @PathVariable("title") String courseTitle) {
        CourseDTO add = courseService.add(courseDTO, courseTitle);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @GetMapping("/lectures/{courseTitle}")
    ResponseEntity<List<LectureDTO>> getAllCoursesOfCategory(@PathVariable("courseTitle") String courseTitle) {
        List<LectureDTO> lecturesOfCourse = courseService.findLecturesOfCourse(courseTitle);
        return new ResponseEntity<>(lecturesOfCourse, HttpStatus.OK);
    }
}
