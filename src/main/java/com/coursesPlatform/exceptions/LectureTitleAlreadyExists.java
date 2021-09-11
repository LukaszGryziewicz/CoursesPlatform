package com.coursesPlatform.exceptions;

public class LectureTitleAlreadyExists  extends RuntimeException {
    public LectureTitleAlreadyExists() {
        super("Lecture title already exists");
    }
}
