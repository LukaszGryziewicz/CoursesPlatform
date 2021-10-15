package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private final List<Course> courses = new ArrayList<>();

    Category() {
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    List<Course> getCourses() {
        return courses;
    }

    void add(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", courses=" + courses +
                '}';
    }
}
