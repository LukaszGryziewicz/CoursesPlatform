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

    Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void add(Course course) {
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
