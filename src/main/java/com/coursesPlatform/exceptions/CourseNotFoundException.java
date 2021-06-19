package com.coursesPlatform.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found");
    }
}
