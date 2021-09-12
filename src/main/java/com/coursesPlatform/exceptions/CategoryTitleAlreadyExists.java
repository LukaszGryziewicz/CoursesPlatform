package com.coursesPlatform.exceptions;

public class CategoryTitleAlreadyExists  extends RuntimeException {
    public CategoryTitleAlreadyExists() {
        super("Category title already exists");
    }
}
