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
    @OneToMany()
    private List<Course> courses = new ArrayList<>();

    Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    Category() {
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
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

    void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
