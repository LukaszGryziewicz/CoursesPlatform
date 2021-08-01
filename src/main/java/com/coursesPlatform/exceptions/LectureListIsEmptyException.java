package com.coursesPlatform.exceptions;

public class LectureListIsEmptyException extends RuntimeException {
    public LectureListIsEmptyException() {
        super("Lecture list is empty");
    }
}
