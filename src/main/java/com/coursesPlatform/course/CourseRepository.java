package com.coursesPlatform.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
   Course findCourseById(Long id);
}
