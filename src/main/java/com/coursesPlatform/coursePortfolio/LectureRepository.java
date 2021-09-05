package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface LectureRepository extends JpaRepository<Lecture, Long> {

    Optional<Lecture> findLectureByTitle(String title);

    List<Lecture> findByTitleIn(List<String> titles);
}
