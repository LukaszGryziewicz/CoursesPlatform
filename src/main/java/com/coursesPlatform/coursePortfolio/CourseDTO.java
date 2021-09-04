package com.coursesPlatform.coursePortfolio;

import java.util.Objects;

public class CourseDTO {
    private String title;
    private String description;

    public CourseDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CourseDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return Objects.equals(title, courseDTO.title) && Objects.equals(description, courseDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
