package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface LectureRepository extends JpaRepository<Lecture, Long> {

    Optional<Lecture> findLectureByTitleAndDescription(String title, String description);
}
