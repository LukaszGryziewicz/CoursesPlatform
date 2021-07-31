package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;
    @Value("${title.length.limit}")
    private int titleLengthLimit;
    @Value("${description.length.limit}")
    private int descriptionLengthLimit;

    LectureService(LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }

    private LectureDTO convertLectureToDTO(Lecture lecture) {
        return new LectureDTO(
                lecture.getTitle(),
                lecture.getDescription(),
                lecture.getPrice(),
                lecture.getDuration()
        );
    }

    private Lecture convertLectureDTOToLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        lecture.setTitle(lectureDTO.getTitle());
        lecture.setDescription(lectureDTO.getDescription());
        lecture.setDuration(lectureDTO.getDuration());
        lecture.setPrice(lectureDTO.getPrice());
        return lecture;
    }

    List<LectureDTO> findAllLectures() {
        return lectureRepository.findAll()
                .stream()
                .map(this::convertLectureToDTO)
                .collect(Collectors.toList());
    }

    LectureDTO add(LectureDTO lectureDTO, String courseTitle) {
        Optional<Course> courseByTitle = courseRepository.findCourseByTitle(courseTitle);
        Course course = courseByTitle.orElseThrow(CourseNotFoundException::new);

        Optional<Lecture> lecture = lectureRepository.findLectureByTitleAndDescription(lectureDTO.getTitle(), lectureDTO.getDescription());
        if ( lecture.isPresent() ) {
            throw new IllegalStateException("Lecture with given title and description already exists");
        }
        if ( lectureDTO.getTitle().length() >= titleLengthLimit ) {
            throw new IllegalLengthException();
        }
        if ( lectureDTO.getDescription().length() >= descriptionLengthLimit ) {
            throw new IllegalLengthException();
        }
        Lecture savedLecture = lectureRepository.save(convertLectureDTOToLecture(lectureDTO));
        course.add(savedLecture);
        courseRepository.save(course);
        return convertLectureToDTO(savedLecture);
    }
}
