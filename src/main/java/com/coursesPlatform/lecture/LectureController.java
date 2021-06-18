package com.coursesPlatform.lecture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@CrossOrigin(origins = "http://localhost:4200/")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> allLectures = lectureService.findAllLectures();
        return new ResponseEntity<>(allLectures, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Lecture> addNewLecture(@RequestBody Lecture lecture){
        Lecture add = lectureService.add(lecture);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
}
