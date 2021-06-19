package com.coursesPlatform.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
   Optional<Course> findCourseById(Long id);
   Optional<Course> findCourseByTitleAndDescription(String title, String description);

   Optional<Course> findCourseByTitle(String title);
}
