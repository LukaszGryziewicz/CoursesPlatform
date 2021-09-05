package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByTitle(String title);
}
