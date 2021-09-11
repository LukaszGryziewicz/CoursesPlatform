package com.coursesPlatform.exceptions;

public class CourseTitleAlreadyExists  extends RuntimeException {
    public CourseTitleAlreadyExists() {
        super("Course title already exists");
    }
}
