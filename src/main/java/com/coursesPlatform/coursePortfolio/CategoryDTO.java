package com.coursesPlatform.coursePortfolio;

import java.util.Objects;

public class CategoryDTO {
    private String title;
    private String description;

    public CategoryDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CategoryDTO() {
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
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
