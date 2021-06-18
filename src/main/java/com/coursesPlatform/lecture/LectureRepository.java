package com.coursesPlatform.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findLectureById(Long id);

    Optional<Lecture>findLectureByTitleAndDescription(String title,String description);
}
