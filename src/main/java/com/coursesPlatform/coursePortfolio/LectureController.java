package com.coursesPlatform.coursePortfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@CrossOrigin(origins = "http://localhost:4200/")
class LectureController {
    private final LectureService lectureService;

    LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/all")
    ResponseEntity<List<LectureDTO>> getAllLectures() {
        List<LectureDTO> allLectures = lectureService.findAllLectures();
        return new ResponseEntity<>(allLectures, HttpStatus.OK);
    }

    @PostMapping("/add/{title}")
    ResponseEntity<LectureDTO> addNewLecture(@RequestBody LectureDTO lectureDTO, @PathVariable("title") String courseTitle) {
        LectureDTO add = lectureService.add(lectureDTO, courseTitle);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
}
